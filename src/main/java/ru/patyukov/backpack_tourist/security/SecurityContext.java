package ru.patyukov.backpack_tourist.security;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Data
@SessionScope
public class SecurityContext {
    private String loginUser = "";
    private String password = "";
    private Long idHike;
}
