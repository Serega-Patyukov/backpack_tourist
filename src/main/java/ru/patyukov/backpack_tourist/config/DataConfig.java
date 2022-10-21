package ru.patyukov.backpack_tourist.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.service.UserService;

@Configuration
@AllArgsConstructor
public class DataConfig {

    private final UserService userService;

    @Bean
    public CommandLineRunner addSuperUser() {
        return args -> {
            if (!userService.existsById("admin")) {
                UserDto userDto = new UserDto();
                userDto.setName("admin");
                userDto.setNotes("admin");
                userDto.setLogin("admin");
                userDto.setAuthority("root");
                userDto.setPassword("SuperUser");
                userDto.setPhoneNumber("89009553902");

                userService.addUser(userDto);
            }
        };
    }
}
