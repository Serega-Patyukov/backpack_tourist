package ru.patyukov.backpack_tourist.web.request;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
    @Size(min = 3, message = "Минимум 3 символа.")
    private String login;

    @Size(min = 8, message = "Минимум 8 символов.")
    private String password;

    @Size(min = 3, message = "Минимум 3 символа.")
    private String name;

    @Pattern(regexp = "^((([+][7])|[8])[0-9]{10})$",
            message = "Должно быть 11 цифр. Первая цифра +7 или 8.")
    private String phoneNumber;

    private String notes;
}
