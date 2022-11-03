package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    EquipmentDto saveEquipment(EquipmentDto equipmentDto);
    List<EquipmentDto> findByHikeId(Long idHike);
    EquipmentDto getEquipment(Long idEquipment);

    void deleteEquipment(Long idEquipment);
}
