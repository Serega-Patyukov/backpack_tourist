package ru.patyukov.backpack_tourist.mapper;

import org.mapstruct.Mapper;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userRequestToUserDto(UserRequest userRequest);
    UserResponse userDtoToUserResponse(UserDto userDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    UserRequest userDtoToUserRequest(UserDto userDto);
}
