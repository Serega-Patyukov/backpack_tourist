package ru.patyukov.backpack_tourist.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.patyukov.backpack_tourist.entity.Authority;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DataConfig {

    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner addSuperUser() {
        return args -> {
            if (!userRepository.existsById("admin")) {
                User user = new User();
                user.setName("admin");
                user.setNotes("admin");
                user.setLogin("admin");

                List<Authority> authorities = new ArrayList<>();
                Authority authority = new Authority();
                authority.setUser(user);
                authority.setName("ROLE_ADMIN");
                authorities.add(authority);

                user.setAuthorities(authorities);
                user.setPassword("12345678");
                user.setPhoneNumber("89009553902");

                userRepository.save(user);
            }
        };
    }
}
