package ru.patyukov.backpack_tourist.web.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserLogPasRequest {

    private long id;

    @Size(min = 3, message = "Введите корректно логин. Минимум 3 символа.")
    private String login = "";

    @Size(min = 8, message = "Введите корректно пароль. Минимум 8 символов.")
    private String password = "";
}
