package ru.patyukov.backpack_tourist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.model.Equipment;
import ru.patyukov.backpack_tourist.model.EquipmentData;
import ru.patyukov.backpack_tourist.services.EquipmentServiceAPI;

@RestController
@RequestMapping("/api")
public class EquipmentControllerAPI {

    @Autowired
    private EquipmentServiceAPI equipmentServiceAPI;

    @PostMapping("/save")
    public Equipment saveEquipment(@RequestBody Equipment equipment) {
        return equipmentServiceAPI.save(equipment);
    }

    @PostMapping("/update/{id}")
    private Equipment updateEquipment(@RequestBody EquipmentData equipmentData, @PathVariable Long id) {

        Equipment buf = equipmentServiceAPI.findById(id);

        buf.setName(equipmentData.getName());
        buf.setGrp(equipmentData.getGrp());
        buf.setWeight(equipmentData.getWeight());
        buf.setNotes(equipmentData.getNotes());

        return equipmentServiceAPI.save(buf);
    }

    @DeleteMapping("id/{id}")
    public void deleteEquipmentById(@PathVariable Long id) {
        equipmentServiceAPI.deleteById(id);
    }

    @DeleteMapping("idHike/{idHike}")
    public void deleteEquipmentAll(@PathVariable Long hikeId) {
        equipmentServiceAPI.deleteAll(equipmentServiceAPI.findByHikeId(hikeId));
    }
}
