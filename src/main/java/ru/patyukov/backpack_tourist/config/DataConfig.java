package ru.patyukov.backpack_tourist.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.patyukov.backpack_tourist.entity.Role;
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

                Role role = new Role();
                role.setUser(user);
                role.setName("ROLE_ADMIN");

                List<Role> roles = new ArrayList<>();
                roles.add(role);

                user.setAuthorities(roles);
                user.setPassword("$2a$10$WGdlDMaJLTtxQERD5h3XsOlEEvoTKPysaVt/NfthUNuqc5qMlkxO6");
                user.setPhoneNumber("89009553902");

                userRepository.save(user);
            }
        };
    }
}
