package ru.patyukov.backpack_tourist.dto;

import lombok.Data;

@Data
public class UserDto {
    private String login;
    private String password;
    private String name;
    private String authority = "user";
    private String phoneNumber;
    private String notes;
}