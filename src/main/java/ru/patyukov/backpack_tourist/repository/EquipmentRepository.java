package ru.patyukov.backpack_tourist.repository;

import org.springframework.data.repository.CrudRepository;
import ru.patyukov.backpack_tourist.entity.Equipment;

import java.util.List;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
    List<Equipment> findByHikeId(Long hikeId);
}
