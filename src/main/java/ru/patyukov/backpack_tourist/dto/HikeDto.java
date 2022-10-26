package ru.patyukov.backpack_tourist.dto;

import lombok.Data;

@Data
public class HikeDto {
    private long id;
    private String name;
    private String dateStart;
    private String dateFinish;
    private String userLogin;
    private String notes;
}
