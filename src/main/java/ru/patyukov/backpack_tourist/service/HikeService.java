package ru.patyukov.backpack_tourist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.entity.User;
import ru.patyukov.backpack_tourist.model.Hike;
import ru.patyukov.backpack_tourist.repository.UserRepository;

@Service
public class HikeService {

    @Autowired
    private UserRepository userRepository;

    public User save(Hike hike, String login) {
        //TODO тут нужно сделать проверку
//        User user = userRepository.findById(login).get();
//        user.getHikeList().add(hike);
//        return userRepository.save(user);
        return new User();
    }

    public boolean existsById(long id, String login) {
        //TODO тут нужно сделать проверку
//        User user = userRepository.findById(login).get();
//        List<Hike> hikeList = user.getHikeList();
//        for (Hike hike : hikeList) {
//            if (hike.getId() == id) {
//                return true;
//            }
//        }
        return false;
    }

    public Hike findById(long id, String login) {
        //TODO тут нужно сделать проверку
//        User user = userRepository.findById(login).get();
//        List<Hike> hikeList = user.getHikeList();
//        for (Hike hike : hikeList) {
//            if (hike.getId() == id) {
//                return hike;
//            }
//        }
        return new Hike();
    }
}
