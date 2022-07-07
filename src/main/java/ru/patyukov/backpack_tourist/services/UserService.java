package ru.patyukov.backpack_tourist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.model.Equipment;
import ru.patyukov.backpack_tourist.model.GroupEquipment;
import ru.patyukov.backpack_tourist.model.Hike;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        //TODO тут нужно сделать проверку
        if (user.getAuthority() == null) user.setAuthority("user");

        Equipment equipment1 = new Equipment();
        equipment1.setName("Рюкзак");
        equipment1.setNotes("Все норм");
        equipment1.setGrp(GroupEquipment.PERSONAL_EQUIPMENT);
        equipment1.setWeight(3.400);

        Hike hike1 = new Hike();
        hike1.setName("Лаго-Наки");
        hike1.setNotes("Возможно пойдем");
        hike1.setDate(LocalDate.now());

        List<Equipment> eh1 = hike1.getEquipmentList();
        eh1.add(equipment1);

        Equipment equipment2 = new Equipment();
        equipment2.setName("Рюкзак");
        equipment2.setNotes("Все норм");
        equipment2.setGrp(GroupEquipment.PERSONAL_EQUIPMENT);
        equipment2.setWeight(3.400);

        Hike hike2 = new Hike();
        hike2.setName("Лаго-Наки");
        hike2.setNotes("Возможно пойдем");
        hike2.setDate(LocalDate.now());

        List<Equipment> eh2 = hike2.getEquipmentList();
        eh2.add(equipment2);

        List<Hike> hikeList = user.getHikeList();
        hikeList.add(hike1);
        hikeList.add(hike2);

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
