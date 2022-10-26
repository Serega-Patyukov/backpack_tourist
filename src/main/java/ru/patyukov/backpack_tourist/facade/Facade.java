package ru.patyukov.backpack_tourist.facade;

import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;
import ru.patyukov.backpack_tourist.web.response.UserLogPasResponse;
import ru.patyukov.backpack_tourist.web.response.UserResponse;


public interface Facade {
    UserResponse addUser(UserRequest UserRequest);
    HikeResponse addHike(HikeRequest hikeRequest, UserLogPasRequest userLogPasRequest);
    UserResponse addUserModel(UserLogPasRequest userLogPasRequest);
    HikeRequest addHikeModel(UserLogPasRequest userLogPasRequest);
    HikeResponse addHikeModel(UserLogPasRequest userLogPasRequest, Long idHike);
    UserLogPasResponse login(UserLogPasRequest userLogPasRequest);
}
