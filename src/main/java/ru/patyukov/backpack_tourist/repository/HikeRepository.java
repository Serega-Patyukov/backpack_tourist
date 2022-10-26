package ru.patyukov.backpack_tourist.repository;

import org.springframework.data.repository.CrudRepository;
import ru.patyukov.backpack_tourist.entity.Hike;

import java.util.List;

public interface HikeRepository extends CrudRepository<Hike, Long> {
    List<Hike> findByUserLogin(String login);
}
