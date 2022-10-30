package ru.patyukov.backpack_tourist.web.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserLogPasRequest {

    private long id;

    @Size(min = 3, message = "Минимум 3 символа.")
    private String login = "";

    @Size(min = 8, message = "Минимум 8 символов.")
    private String password = "";
}
