package ru.patyukov.backpack_tourist.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.mapper.UserMapper;
import ru.patyukov.backpack_tourist.service.UserService;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.UserLogPasResponse;
import ru.patyukov.backpack_tourist.web.response.UserResponse;

@Component
@AllArgsConstructor
public class FacadeImpl implements Facade {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        if (userService.existsById(userRequest.getLogin())) {
            userRequest.setError(true);
            throw new BadRequestException("redirect:/addUser");
        }
        UserDto userDto = userService.addUser(userMapper.userRequestToUserDto(userRequest));
        return userMapper.userDtoToUserResponse(userDto);
    }

    @Override
    public UserLogPasResponse login(UserLogPasRequest userLogPasRequest) {
        if (userService.existsById(userLogPasRequest.getLogin())) {
            UserDto userDto = userService.findById(userLogPasRequest.getLogin());
            if (userLogPasRequest.getPassword().equals(userDto.getPassword())) {
                userLogPasRequest.setError(false);
                return userMapper.userLogPasRequestToUserLogPasResponse(userLogPasRequest);
            }
        }
        userLogPasRequest.setError(true);
        throw new BadRequestException("redirect:/login");
    }

    @Override
    public UserResponse addUserModel(UserLogPasRequest userLogPasRequest) {
        login(userLogPasRequest);
        UserDto userDto = userService.findById(userLogPasRequest.getLogin());
        return userMapper.userDtoToUserResponse(userDto);
    }
}
