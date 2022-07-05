package ru.patyukov.backpack_tourist.model;

import java.time.LocalDate;
import java.util.List;

// Класс поход
public class Hike {
    private long id;
    private String name;
    private LocalDate date;
    private List<Equipment> equipmentList;   // Список снаряжения
    private String notes;   // Примечания
}
