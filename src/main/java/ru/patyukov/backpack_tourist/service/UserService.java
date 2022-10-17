package ru.patyukov.backpack_tourist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        //TODO тут нужно сделать проверку
        if (user.getAuthority() == null) user.setAuthority("user");

        return userRepository.save(user);
    }

    public boolean existsById(String login) {
        //TODO тут нужно сделать проверку
        return userRepository.existsById(login);
    }

    public User findById(String login) {
        //TODO тут нужно сделать проверку
        return userRepository.findById(login).get();
    }
}
