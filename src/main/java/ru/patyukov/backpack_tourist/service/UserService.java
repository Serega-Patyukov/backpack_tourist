package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.UserDto;

public interface UserService {
    UserDto saveUser(UserDto user);
    boolean existsById(String login);
    UserDto getUser(String login);

    UserDto updateUser(UserDto userRequestToUserDto);

    void deleteUser(String login);
}
