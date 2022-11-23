package ru.patyukov.backpack_tourist.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.patyukov.backpack_tourist.dto.EquipmentDto;
import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.mapper.EquipmentMapper;
import ru.patyukov.backpack_tourist.mapper.HikeMapper;
import ru.patyukov.backpack_tourist.mapper.UserMapper;
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

@Slf4j
@Component
@AllArgsConstructor
public class FacadeImpl implements Facade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final HikeService hikeService;
    private final HikeMapper hikeMapper;
    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        UserDto userDto = userService.saveUser(userMapper.userRequestToUserDto(userRequest));
        return userMapper.userDtoToUserResponse(userDto);
    }
    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        UserDto userDto = userService.updateUser(userMapper.userRequestToUserDto(userRequest));
        return userMapper.userDtoToUserResponse(userDto);
    }
    @Override
    public UserResponse getUserResponse() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        List<HikeDto> hikeDtoList = hikeService.findByUserLogin(login);
        List<HikeResponse> hikeResponseList = hikeDtoList.stream()
                .map(hikeDto -> hikeMapper.hikeDtoToHikeResponse(hikeDto))
                .collect(Collectors.toList());

        UserDto userDto = userService.getUser(login);
        UserResponse userResponse = userMapper.userDtoToUserResponse(userDto);
        userResponse.setHikeList(hikeResponseList);

        return userResponse;
    }
    @Override
    public UserRequest getUserRequest(String login) {
        UserDto userDto = userService.getUser(login);
        return userMapper.userDtoToUserRequest(userDto);
    }
    @Override
    public void deleteUser(String login) {
        userService.deleteUser(login);
    }



    @Override
    public HikeResponse saveHike(HikeRequest hikeRequest) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        HikeDto hikeDto = hikeMapper.hikeRequestToHikeDto(hikeRequest);
        hikeDto.setUserLogin(login);
        return hikeMapper.hikeDtoToHikeResponse(hikeService.saveHike(hikeDto));
    }
    @Override
    public HikeResponse getHikeResponse(Long idHike) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        HikeDto hikeDto = hikeService.getHike(idHike);
        if (!login.equals(hikeDto.getUserLogin())) {
            throw new BadRequestException("user redirect:/user");
        }

        List<EquipmentDto> equipmentDtoList = equipmentService.findByIdHike(idHike);

        HikeResponse hikeResponse = hikeMapper.hikeDtoToHikeResponse(hikeDto);
        hikeResponse.setEquipmentList(equipmentDtoList.stream()
                .map(equipmentDto -> equipmentMapper.equipmentDtoToEquipmentResponse(equipmentDto))
                .collect(Collectors.toList()));

        return hikeResponse;
    }
    @Override
    public HikeRequest getHikeRequest(Long idHike) {
        HikeDto hikeDto = hikeService.getHike(idHike);
        return hikeMapper.hikeDtoToHikeRequest(hikeDto);
    }
    @Override
    public void deleteHike(Long idHike) {
        hikeService.deleteHike(idHike);
    }



    @Override
    public EquipmentResponse saveEquipment(EquipmentRequest equipmentRequest) {
        EquipmentDto equipmentDto = equipmentMapper.equipmentRequestToEquipmentDto(equipmentRequest);
        return equipmentMapper.equipmentDtoToEquipmentResponse(equipmentService.saveEquipment(equipmentDto));
    }
    @Override
    public EquipmentRequest getEquipmentRequest(Long idEquipment) {
        EquipmentDto equipmentDto = equipmentService.getEquipment(idEquipment);
        return equipmentMapper.equipmentDtoToEquipmentRequest(equipmentDto);
    }
    @Override
    public void deleteEquipment(Long idEquipment) {
        equipmentService.deleteEquipment(idEquipment);
    }
}
