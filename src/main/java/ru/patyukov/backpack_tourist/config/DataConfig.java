package ru.patyukov.backpack_tourist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.services.UserService;

@Configuration
public class DataConfig {

    @Autowired
    private UserService userService;

    @Bean
    public CommandLineRunner addSuperUser() {
        return args -> {
            if (!userService.existsById("admin")) {
                User user = new User();
                user.setName("admin");
                user.setNotes("admin");
                user.setLogin("admin");
                user.setAuthority("root");
                user.setPassword("SuperUser");
                user.setPhoneNumber("89009553902");

                userService.save(user);
            }
        };
    }
}
