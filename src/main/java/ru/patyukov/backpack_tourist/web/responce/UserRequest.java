package ru.patyukov.backpack_tourist.web.responce;

import ru.patyukov.backpack_tourist.model.Hike;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
    @Id
    @NotNull
    @Size(min = 3, message = "Введите корректно логин. Минимум 3 символа.")
    private String login;

    @NotNull
    @Size(min = 8, message = "Введите корректно пароль. Минимум 8 символов.")
    private String password;

    @NotNull
    @Size(min = 3, message = "Введите корректно имя. Минимум 3 символа.")
    private String name;

    //TODO с этим полем нужно что то сделать !!!
    @Transient
    private boolean isError = false;

    @NotNull
    @Pattern(regexp = "^((([+][7])|[8])[0-9]{10})$" ,
            message = "Введите корректно номер телефона. Должно быть 11 цифр.")
    @Column(name = "phone_number")
    private String phoneNumber;   // Номер телефона

    private String notes = "";   // Примечания
}
