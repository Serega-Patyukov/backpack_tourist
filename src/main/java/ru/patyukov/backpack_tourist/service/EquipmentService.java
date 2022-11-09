package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    EquipmentDto saveEquipment(EquipmentDto equipmentDto);
    EquipmentDto getEquipment(Long idEquipment);
    List<EquipmentDto> findByIdHike(Long idHike);
    void deleteEquipment(Long idEquipment);
}
