package ru.patyukov.backpack_tourist.mapper;

import org.mapstruct.Mapper;
import ru.patyukov.backpack_tourist.dto.EquipmentDto;
import ru.patyukov.backpack_tourist.entity.Equipment;
import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    EquipmentResponse equipmentDtoToEquipmentResponse(EquipmentDto equipmentDto);
    EquipmentRequest equipmentDtoToEquipmentRequest(EquipmentDto equipmentDto);

    EquipmentDto equipmentRequestToEquipmentDto(EquipmentRequest equipmentRequest);
    EquipmentDto EquipmentToEquipmentDto(Equipment equipment);

    Equipment equipmentDtoToEquipment(EquipmentDto equipmentDto);
}
