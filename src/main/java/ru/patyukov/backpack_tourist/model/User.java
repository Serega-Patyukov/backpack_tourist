package ru.patyukov.backpack_tourist.model;

import java.util.List;

public class User {
    private long id;
    private String name;
    private String login;
    private String password;
    private String authority;   // Права доступа
    private List<Hike> hikeList;   // Список походов
    private String phone_number;   // Номер телефона
    private String notes;   // Примечания
}
