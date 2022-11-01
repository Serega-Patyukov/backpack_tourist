package ru.patyukov.backpack_tourist.facade;

import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;
import ru.patyukov.backpack_tourist.web.response.UserResponse;


public interface Facade {
    UserResponse addUser(UserRequest UserRequest);
    HikeResponse addHike(HikeRequest hikeRequest);
    UserResponse addUserModel();
    HikeRequest addHikeModel();
    HikeResponse addHikeModel(Long idHike);
    EquipmentRequest addEquipmentModel();
    EquipmentResponse addEquipment(EquipmentRequest equipmentRequest, Long idHike);
    EquipmentRequest editEquipmentModel(Long idHike, Long idEquipment);
    void deleteEquipment(Long idEquipment);
    void login();
}
