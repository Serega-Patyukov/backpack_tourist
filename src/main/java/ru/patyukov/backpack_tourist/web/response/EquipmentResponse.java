package ru.patyukov.backpack_tourist.web.response;

import lombok.Data;
import ru.patyukov.backpack_tourist.entity.GroupEquipment;
import ru.patyukov.backpack_tourist.entity.Hike;

import javax.persistence.*;

@Data
public class EquipmentResponse {
    private long id;
    private String name;
    private GroupEquipment grp;
    private double weight;
    private String notes;
}
