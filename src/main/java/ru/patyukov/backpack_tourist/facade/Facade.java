package ru.patyukov.backpack_tourist.facade;

import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;
import ru.patyukov.backpack_tourist.web.response.UserResponse;


public interface Facade {
    UserResponse saveUser(UserRequest UserRequest);
    UserResponse updateUser(UserRequest userRequest);
    UserResponse getUserResponse();
    UserRequest getUserRequest(String login);
    void deleteUser(String login);



    HikeResponse saveHike(HikeRequest hikeRequest);
    HikeResponse getHikeResponse(Long idHike);
    HikeRequest getHikeRequest(Long idHike);
    void deleteHike(Long idHike);



    EquipmentResponse saveEquipment(EquipmentRequest equipmentRequest);
    EquipmentRequest getEquipmentRequest(Long idEquipment);
    void deleteEquipment(Long idEquipment);



    void login();
}
