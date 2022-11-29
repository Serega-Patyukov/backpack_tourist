package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.UserDto;

public interface UserService {
    UserDto saveUser(UserDto user);
    UserDto updateUser(UserDto userRequestToUserDto);
    UserDto getUser();
    boolean existsById(String login);
    void deleteUser();
}
