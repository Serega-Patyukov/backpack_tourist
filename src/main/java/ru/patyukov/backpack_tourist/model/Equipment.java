package ru.patyukov.backpack_tourist.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Класс снаряжения
@Data
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, message = "Введите корректно имя")
    private String name;

    @Enumerated(EnumType.STRING)
    private GroupEquipment grp;   // Группа снаряжения

    private double weight;   // Масса
    private String notes;   // Примечания

    @ManyToOne
    @JoinColumn(name = "hikeId", nullable = false)
    private Hike hikeId;
}
