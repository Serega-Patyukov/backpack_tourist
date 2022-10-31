package ru.patyukov.backpack_tourist.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.patyukov.backpack_tourist.dto.EquipmentDto;
import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.mapper.EquipmentMapper;
import ru.patyukov.backpack_tourist.mapper.HikeMapper;
import ru.patyukov.backpack_tourist.mapper.UserMapper;
import ru.patyukov.backpack_tourist.security.SecurityContext;
import ru.patyukov.backpack_tourist.service.EquipmentService;
import ru.patyukov.backpack_tourist.service.HikeService;
import ru.patyukov.backpack_tourist.service.UserService;
import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;
import ru.patyukov.backpack_tourist.web.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class FacadeImpl implements Facade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final HikeService hikeService;
    private final HikeMapper hikeMapper;
    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;
    private final SecurityContext securityContext;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        log.info("Получил запрос на создание пользователя " + userRequest);
        UserDto userDto = userService.addUser(userMapper.userRequestToUserDto(userRequest));
        return userMapper.userDtoToUserResponse(userDto);
    }

    @Override
    public HikeResponse addHike(HikeRequest hikeRequest) {
        login();
        String login = securityContext.getLoginUser();

        HikeDto hikeDto = hikeMapper.hikeRequestToHikeDto(hikeRequest);
        hikeDto.setUserLogin(login);
        return hikeMapper.hikeDtoToHikeResponse(hikeService.addHike(hikeDto));
    }

    @Override
    public UserResponse addUserModel() {
        login();
        String login = securityContext.getLoginUser();

        List<HikeDto> hikeDtoList = hikeService.findByUserLogin(login);
        List<HikeResponse> hikeResponseList = hikeDtoList.stream()
                .map(hikeDto -> hikeMapper.hikeDtoToHikeResponse(hikeDto))
                .collect(Collectors.toList());

        UserDto userDto = userService.findById(login);
        UserResponse userResponse = userMapper.userDtoToUserResponse(userDto);
        userResponse.setHikeList(hikeResponseList);

        return userResponse;
    }

    @Override
    public HikeRequest addHikeModel() {
        login();
        return new HikeRequest();
    }

    @Override
    public HikeResponse addHikeModel(Long idHike) {
        login();
        String login = securityContext.getLoginUser();

        HikeDto hikeDto = hikeService.findById(idHike);
        if (!login.equals(hikeDto.getUserLogin())) {
            throw new BadRequestException("user redirect:/user");
        }

        List<EquipmentDto> equipmentDtoList = equipmentService.findByHikeId(idHike);

        HikeResponse hikeResponse = hikeMapper.hikeDtoToHikeResponse(hikeDto);
        hikeResponse.setEquipmentList(equipmentDtoList.stream()
                .map(equipmentDto -> equipmentMapper.equipmentDtoToEquipmentResponse(equipmentDto))
                .collect(Collectors.toList()));

        return hikeResponse;
    }

    @Override
    public EquipmentRequest addEquipmentModel() {
        return new EquipmentRequest();
    }

    @Override
    public EquipmentResponse addEquipment(EquipmentRequest equipmentRequest, Long idHike) {
        login();
        String login = securityContext.getLoginUser();

        HikeDto hikeDto = hikeService.findById(idHike);
        if (!login.equals(hikeDto.getUserLogin())) {
            throw new BadRequestException("user redirect:/user");
        }

        EquipmentDto equipmentDto = equipmentMapper.equipmentRequestToEquipmentDto(equipmentRequest);
        equipmentDto.setHikeId(idHike);
        return equipmentMapper.equipmentDtoToEquipmentResponse(equipmentService.addEquipment(equipmentDto));
    }

    @Override
    public void login() {
        String login = securityContext.getLoginUser();
        String password = securityContext.getPassword();

        if (userService.existsById(login)) {
            UserDto userDto = userService.findById(login);
            if (password.equals(userDto.getPassword())) {
                return;
            }
        }
        throw new BadRequestException("login redirect:/login");
    }
}
