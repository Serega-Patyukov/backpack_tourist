package ru.patyukov.backpack_tourist.web.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private String name;
    private List<HikeResponse> hikeList = new ArrayList<>();
    private String phoneNumber;
    private String notes;
}
