package ru.patyukov.backpack_tourist.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

// Класс поход
@Data
@Entity
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hikeId;
    private String name;
    private LocalDate date;

    @OneToMany(mappedBy = "hikeId", cascade = CascadeType.ALL)
    private List<Equipment> equipmentList;   // Список снаряжения
    private String notes;   // Примечания

    @ManyToOne
    @JoinColumn(name = "login", nullable = false)
    private User userLogin;
}
