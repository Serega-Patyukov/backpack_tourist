package ru.patyukov.backpack_tourist.mapper;

import org.mapstruct.Mapper;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse userDtoToUserResponse(UserDto userDto);
    UserRequest userDtoToUserRequest(UserDto userDto);
    UserDto userRequestToUserDto(UserRequest userRequest);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
