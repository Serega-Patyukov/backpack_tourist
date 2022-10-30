package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.dto.EquipmentDto;
import ru.patyukov.backpack_tourist.entity.Equipment;
import ru.patyukov.backpack_tourist.entity.Hike;
import ru.patyukov.backpack_tourist.mapper.EquipmentMapper;
import ru.patyukov.backpack_tourist.repository.EquipmentRepository;

@Service
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    @Override
    public EquipmentDto addEquipment(EquipmentDto equipmentDto) {
        Hike hike = new Hike();
        hike.setId(equipmentDto.getHikeId());
        Equipment equipment = equipmentMapper.equipmentDtoToEquipment(equipmentDto);
        equipment.setHike(hike);

        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.EquipmentToEquipmentDto(equipment);
    }
}
