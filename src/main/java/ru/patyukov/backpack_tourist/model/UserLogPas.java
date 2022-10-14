package ru.patyukov.backpack_tourist.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserLogPas {

    private long id;

    @NotNull
    @Size(min = 3, message = "Введите корректно логин. Минимум 3 символа.")
    private String login;

    @NotNull
    @Size(min = 8, message = "Введите корректно пароль. Минимум 8 символов.")
    private String password;

    //TODO с этим полем нужно что то сделать !!!
    private boolean isError = false;
}
