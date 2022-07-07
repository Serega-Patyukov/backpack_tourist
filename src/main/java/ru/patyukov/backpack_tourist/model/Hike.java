package ru.patyukov.backpack_tourist.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Класс поход
@Data
@Entity
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "hike_id")
    private List<Equipment> equipmentList = new ArrayList<>();   // Список снаряжения

    private String notes;   // Примечания
}
