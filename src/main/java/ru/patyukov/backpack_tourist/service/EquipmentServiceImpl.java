package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.dto.EquipmentDto;
import ru.patyukov.backpack_tourist.entity.Equipment;
import ru.patyukov.backpack_tourist.entity.Hike;
import ru.patyukov.backpack_tourist.mapper.EquipmentMapper;
import ru.patyukov.backpack_tourist.repository.EquipmentRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public EquipmentDto getEquipment(Long id) {
        Equipment equipment = equipmentRepository.findById(id).get();
        EquipmentDto equipmentDto = equipmentMapper.EquipmentToEquipmentDto(equipment);
        equipmentDto.setHikeId(equipment.getHike().getId());
        return equipmentDto;
    }

    @Override
    public List<EquipmentDto> findByHikeId(Long hikeId) {
        return equipmentRepository.findByHikeId(hikeId).stream()
                .map(equipment -> equipmentMapper.EquipmentToEquipmentDto(equipment))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEquipment(Long idEquipment) {
        equipmentRepository.deleteById(idEquipment);
    }
}
