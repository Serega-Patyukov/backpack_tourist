package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto user);
    boolean existsById(String login);
    UserDto findById(String login);
}
