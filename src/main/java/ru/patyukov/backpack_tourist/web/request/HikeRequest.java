package ru.patyukov.backpack_tourist.web.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class HikeRequest {
    private long id;
    @Size(min = 3, message = "Введите корректно имя. Минимум 3 символа.")
    private String name;
    private String dateStart = "";
    private String dateFinish = "";
    private String userLogin;
    private String notes = "";
}
