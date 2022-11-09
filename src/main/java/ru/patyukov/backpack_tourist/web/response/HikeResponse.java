package ru.patyukov.backpack_tourist.web.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HikeResponse {
    private long id;
    private String name;
    private String dateStart;
    private String dateFinish;
    private List<EquipmentResponse> equipmentList = new ArrayList<>();
    private String userLogin;
    private String notes;
}
