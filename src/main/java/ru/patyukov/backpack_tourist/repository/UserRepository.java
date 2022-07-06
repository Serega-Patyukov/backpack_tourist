package ru.patyukov.backpack_tourist.repository;

import org.springframework.data.repository.CrudRepository;
import ru.patyukov.backpack_tourist.model.User;

public interface UserRepository extends CrudRepository<User, String> {
}
