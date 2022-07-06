package ru.patyukov.backpack_tourist.model;

import lombok.Data;

// Класс снаряжения для редактирования
@Data
public class EquipmentData {
    private String name;
    private GroupEquipment grp;   // Группа снаряжения
    private double weight;   // Масса
    private String notes;   // Примечания
}
