package ru.patyukov.backpack_tourist.service;

import ru.patyukov.backpack_tourist.dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    EquipmentDto addEquipment(EquipmentDto equipmentDto);
    List<EquipmentDto> findByHikeId(Long hikeId);
}
