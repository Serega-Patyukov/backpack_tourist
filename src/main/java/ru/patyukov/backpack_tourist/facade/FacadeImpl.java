package ru.patyukov.backpack_tourist.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.patyukov.backpack_tourist.dto.HikeDto;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.mapper.HikeMapper;
import ru.patyukov.backpack_tourist.mapper.UserMapper;
import ru.patyukov.backpack_tourist.service.HikeService;
import ru.patyukov.backpack_tourist.service.UserService;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;
import ru.patyukov.backpack_tourist.web.response.UserLogPasResponse;
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

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        log.info("Получил запрос на создание пользователя " + userRequest);
        UserDto userDto = userService.addUser(userMapper.userRequestToUserDto(userRequest));
        return userMapper.userDtoToUserResponse(userDto);
    }

    @Override
    public UserLogPasResponse login(UserLogPasRequest userLogPasRequest) {
        if (userService.existsById(userLogPasRequest.getLogin())) {
            UserDto userDto = userService.findById(userLogPasRequest.getLogin());
            if (userLogPasRequest.getPassword().equals(userDto.getPassword())) {
                return userMapper.userLogPasRequestToUserLogPasResponse(userLogPasRequest);
            }
        }
        throw new BadRequestException("login redirect:/login");
    }

    @Override
    public UserResponse addUserModel(UserLogPasRequest userLogPasRequest) {
        login(userLogPasRequest);

        List<HikeDto> hikeDtoList = hikeService.findByUserLogin(userLogPasRequest.getLogin());
        List<HikeResponse> hikeResponseList = hikeDtoList.stream()
                .map(hikeDto -> hikeMapper.hikeDtoToHikeResponse(hikeDto))
                .collect(Collectors.toList());

        UserDto userDto = userService.findById(userLogPasRequest.getLogin());
        UserResponse userResponse = userMapper.userDtoToUserResponse(userDto);
        userResponse.setHikeList(hikeResponseList);

        return userResponse;
    }

    @Override
    public HikeRequest addHikeModel(UserLogPasRequest userLogPasRequest) {
        login(userLogPasRequest);
        return new HikeRequest();
    }

    @Override
    public HikeResponse addHike(HikeRequest hikeRequest, UserLogPasRequest userLogPasRequest) {
        login(userLogPasRequest);
        HikeDto hikeDto = hikeMapper.hikeRequestToHikeDto(hikeRequest);
        hikeDto.setUserLogin(userLogPasRequest.getLogin());
        return hikeMapper.hikeDtoToHikeResponse(hikeService.addHike(hikeDto));
    }

}
