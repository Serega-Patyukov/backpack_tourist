package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.entity.Authority;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.mapper.UserMapper;
import ru.patyukov.backpack_tourist.repository.UserRepository;
import ru.patyukov.backpack_tourist.security.SecurityContext;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityContext securityContext;

    @Override
    public UserDto saveUser(UserDto userDto) {
        if (existsById(userDto.getLogin())) {
            throw new BadRequestException("addUser redirect:/home");
        }

        User user = userMapper.userDtoToUser(userDto);

        List<Authority> authorities = new ArrayList<>();
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setName("USER");
        authorities.add(authority);

        user.setAuthorities(authorities);

        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.save(userMapper.userDtoToUser(userDto));
        securityContext.setPassword(user.getPassword());
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUser(String login) {
        User user = userRepository.findById(login)
                .orElseThrow(() -> new BadRequestException("login redirect:/login"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public boolean existsById(String login) {
        return userRepository.existsById(login);
    }

    @Override
    public void deleteUser(String login) {
        userRepository.deleteById(login);
        securityContext.setLoginUser("");
        securityContext.setPassword("");
    }
}
