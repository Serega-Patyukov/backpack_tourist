package ru.patyukov.backpack_tourist.web.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class HikeRequest {
    @Size(min = 3, message = "Введите корректно имя. Минимум 3 символа.")
    private String name;
    private String dateStart = "";
    private String dateFinish = "";
    private String notes = "";
}