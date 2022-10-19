package ru.patyukov.backpack_tourist.repository;

import org.springframework.data.repository.CrudRepository;
import ru.patyukov.backpack_tourist.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
}
