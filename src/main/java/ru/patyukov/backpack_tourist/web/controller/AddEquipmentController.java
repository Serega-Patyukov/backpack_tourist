package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/addEquipment")
@AllArgsConstructor
public class AddEquipmentController {

    private final Facade facade;

    @GetMapping
    public String addViewEquipment() {
        return "addEquipment";
    }

    @ModelAttribute
    public void addModel(Model model,
                         @RequestParam(required = false, defaultValue = "0") Long idHike) {
        model.addAttribute("equipmentRequest", facade.addEquipmentModel());
        model.addAttribute("hikeResponse", facade.addHikeModel(idHike));
    }

    @PostMapping
    public String addEquipment(@Valid EquipmentRequest equipmentRequest, Errors errors, Long idHike) {
        if (errors.hasErrors()) return "addEquipment";
        facade.addEquipment(equipmentRequest, idHike);
        return "redirect:/hike?idHike=" + idHike;
    }
}
