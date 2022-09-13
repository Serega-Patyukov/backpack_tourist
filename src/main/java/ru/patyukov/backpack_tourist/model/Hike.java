package ru.patyukov.backpack_tourist.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Класс поход
@Data
@Entity
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 3, message = "Введите корректно имя. Минимум 3 символа.")
    private String name;
    private String dateStart;
    private String dateFinish;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Equipment> equipmentList = new ArrayList<>();   // Список снаряжения

    private String notes;   // Примечания
}
