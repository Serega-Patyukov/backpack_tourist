package ru.patyukov.backpack_tourist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.model.Hike;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.model.UserLogPas;
import ru.patyukov.backpack_tourist.repository.UserRepository;

@Service
public class HikeService {

    @Autowired
    private UserRepository userRepository;

    public User save(Hike hike, UserLogPas userLogPas) {
        //TODO тут нужно сделать проверку
        User user = userRepository.findById(userLogPas.getLogin()).get();
        user.getHikeList().add(hike);
        return userRepository.save(user);
    }
}
