package ru.patyukov.backpack_tourist.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @Size(min = 3, message = "Введите корректно логин. Минимум 3 символа.")
    private String login;

    @NotNull
    @Size(min = 3, message = "Введите корректно имя. Минимум 3 символа.")
    private String name;

    @Transient
    private boolean loginIsEmpty;

    @NotNull
    @Size(min = 8, message = "Введите корректно пароль. Минимум 8 символов.")
    private String password;

    private String authority;   // Права доступа

    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL)
    private List<Hike> hikeList;   // Список походов

    @NotNull
    @Pattern(regexp = "^([0-9])([0-9])([0-9])([0-9])([0-9])([0-9])([0-9])([0-9])([0-9])([0-9])([0-9])$" , message = "Введите корректно номер телефона. Должно быть 11 цифр.")
    private String phone_number;   // Номер телефона

    private String notes;   // Примечания
}
