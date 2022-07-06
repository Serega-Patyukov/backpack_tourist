package ru.patyukov.backpack_tourist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patyukov.backpack_tourist.model.Equipment;
import ru.patyukov.backpack_tourist.repository.EquipmentRepository;

import java.util.List;

@Service
public class EquipmentServiceAPI {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public void deleteById(Long id) {
        if (equipmentRepository.existsById(id)) equipmentRepository.deleteById(id);
        else throw new RuntimeException();
    }

    public void deleteAll(Iterable<Equipment> entities) {
        equipmentRepository.deleteAll(entities);
    }

    public List<Equipment> findByHikeId(Long hikeId) {
        return equipmentRepository.findByHikeId(hikeId);
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).get();
    }
}
