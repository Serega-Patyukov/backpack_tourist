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
    public EquipmentDto saveEquipment(EquipmentDto equipmentDto) {
        Hike hike = new Hike();
        hike.setId(equipmentDto.getIdHike());

        Equipment equipment = equipmentMapper.equipmentDtoToEquipment(equipmentDto);
        equipment.setHike(hike);

        return equipmentMapper.EquipmentToEquipmentDto(equipmentRepository.save(equipment));
    }

    @Override
    public EquipmentDto getEquipment(Long idEquipment) {
        Equipment equipment = equipmentRepository.findById(idEquipment).get();
        EquipmentDto equipmentDto = equipmentMapper.EquipmentToEquipmentDto(equipment);
        equipmentDto.setIdHike(equipment.getHike().getId());
        return equipmentDto;
    }

    @Override
    public List<EquipmentDto> findByHikeId(Long idHike) {
        return equipmentRepository.findByHikeId(idHike).stream()
                .map(equipment -> equipmentMapper.EquipmentToEquipmentDto(equipment))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEquipment(Long idEquipment) {
        equipmentRepository.deleteById(idEquipment);
    }
}
