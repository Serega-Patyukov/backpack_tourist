package ru.patyukov.backpack_tourist.repository;

import org.springframework.data.repository.CrudRepository;
import ru.patyukov.backpack_tourist.model.Hike;

public interface HikeRepository extends CrudRepository<Hike, Long> {
}
