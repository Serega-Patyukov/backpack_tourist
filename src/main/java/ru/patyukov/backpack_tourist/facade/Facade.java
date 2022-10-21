package ru.patyukov.backpack_tourist.facade;

import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.UserLogPasResponse;
import ru.patyukov.backpack_tourist.web.response.UserResponse;


public interface Facade {
    UserResponse addUser(UserRequest UserRequest);
    UserLogPasResponse login(UserLogPasRequest userLogPasRequest);
    UserResponse addUserModel(UserLogPasRequest userLogPasRequest);
}
