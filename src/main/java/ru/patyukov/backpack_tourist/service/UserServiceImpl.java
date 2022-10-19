package ru.patyukov.backpack_tourist.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.dto.UserDto;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.mapper.UserMapper;
import ru.patyukov.backpack_tourist.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(UserDto userDto) {
        User user = userRepository.save(userMapper.userDtoToUserEntity(userDto));
        return userMapper.userEntityToUserDto(user);
    }

    @Override
    public boolean existsById(String login) {
        return userRepository.existsById(login);
    }

    @Override
    public UserDto findById(String login) {
        //TODO тут нужно сделать проверку
        User user = userRepository.findById(login).get();
        return userMapper.userEntityToUserDto(user);
    }
}