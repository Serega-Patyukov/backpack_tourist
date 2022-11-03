package ru.patyukov.backpack_tourist.dto;

import lombok.Data;
import ru.patyukov.backpack_tourist.entity.GroupEquipment;

@Data
public class EquipmentDto {
    private long id;
    private String name;
    private GroupEquipment grp;
    private Long idHike;
    private double weight;
    private String notes;
}
