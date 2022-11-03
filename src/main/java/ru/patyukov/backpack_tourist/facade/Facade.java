package ru.patyukov.backpack_tourist.facade;

import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;
import ru.patyukov.backpack_tourist.web.response.UserResponse;


public interface Facade {
    UserResponse saveUser(UserRequest UserRequest);
    HikeResponse saveHike(HikeRequest hikeRequest);
    UserResponse getUserResponse();
    HikeResponse addHikeModel(Long idHike);
    EquipmentResponse saveEquipment(EquipmentRequest equipmentRequest);
    EquipmentRequest getEquipmentRequest(Long idEquipment);
    void deleteEquipment(Long idEquipment);
    HikeRequest getHikeRequest(Long idHike);
    void login();
    void deleteHike(Long idHike);
    UserRequest getUserRequest(String login);
    UserResponse updateUser(UserRequest userRequest);
    void deleteUser(String login);
}
