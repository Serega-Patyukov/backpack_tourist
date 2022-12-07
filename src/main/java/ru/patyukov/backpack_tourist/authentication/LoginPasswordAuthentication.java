package ru.patyukov.backpack_tourist.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginPasswordAuthentication extends UsernamePasswordAuthenticationToken {
    public LoginPasswordAuthentication(
            Object principal,
            Object credentials) {
        super(principal, credentials);
    }

    public LoginPasswordAuthentication(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
