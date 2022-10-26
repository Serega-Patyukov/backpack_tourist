package ru.patyukov.backpack_tourist.web.response;

import lombok.Data;

@Data
public class HikeResponse {
    private long id;
    private String name;
    private String dateStart = "";
    private String dateFinish = "";
    private String notes = "";
}
