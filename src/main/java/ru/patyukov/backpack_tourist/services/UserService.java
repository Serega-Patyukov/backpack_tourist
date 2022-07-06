package ru.patyukov.backpack_tourist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        user.setAuthority("user");
        return userRepository.save(user);
    }

    public boolean existsById(String login) {
        return userRepository.existsById(login);
    }
}
