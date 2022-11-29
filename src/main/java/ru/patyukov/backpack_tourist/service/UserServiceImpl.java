package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.entity.Role;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.mapper.UserMapper;
import ru.patyukov.backpack_tourist.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(UserDto userDto) {
        if (existsById(userDto.getLogin())) {
            throw new BadRequestException("addUser redirect:/home");
        }

        User user = userMapper.userDtoToUser(userDto);

        Role role = new Role();
        role.setUser(user);
        role.setName("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setAuthorities(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        userDto.setLogin(login);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = userRepository.save(userMapper.userDtoToUser(userDto));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        User user = userRepository.findById(login).get();

        UserDto userDto = userMapper.userToUserDto(user);

        return userDto;
    }

    @Override
    public boolean existsById(String login) {
        return userRepository.existsById(login);
    }

    @Override
    public void deleteUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String login = securityContext.getAuthentication().getName();

        userRepository.deleteById(login);
    }
}
