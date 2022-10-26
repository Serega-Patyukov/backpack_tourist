package ru.patyukov.backpack_tourist.web.response;

import lombok.Data;

@Data
public class UserLogPasResponse {
    private long id;
    private String login = "";
    private String password = "";
}
