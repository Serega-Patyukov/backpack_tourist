package ru.patyukov.backpack_tourist.repository;

import org.springframework.data.repository.CrudRepository;
import ru.patyukov.backpack_tourist.model.Equipment;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
}
