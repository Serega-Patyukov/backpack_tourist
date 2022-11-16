package ru.patyukov.backpack_tourist.web.request;

import lombok.Data;
import ru.patyukov.backpack_tourist.entity.GroupEquipment;

import javax.validation.constraints.Size;

@Data
public class EquipmentRequest {
    private long id;
    @Size(min = 3, message = "Минимум 3 символа.")
    private String name;
    private GroupEquipment grp;
    private Long idHike;
    private double weight;
    private String notes;
}
