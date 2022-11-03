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
@AllArgsConstructor
@RequestMapping("/addEquipment")
public class AddEquipmentController {

    private final Facade facade;

    @GetMapping
    public String viewAddEquipment() {
        return "addEquipment";
    }

    @ModelAttribute
    public void addModel(Model model, Long idHike) {

        //todo тут нужно проверить аутентифицированного пользователя и есть ли у него поход с указанным id.

        EquipmentRequest equipmentRequest = new EquipmentRequest();
        equipmentRequest.setIdHike(idHike);
        model.addAttribute("equipmentRequest", equipmentRequest);
    }

    @PostMapping
    public String saveEquipment(@Valid EquipmentRequest equipmentRequest, Errors errors) {
        if (errors.hasErrors()) return "addEquipment";
        facade.saveEquipment(equipmentRequest);
        return "redirect:/hike?idHike=" + equipmentRequest.getIdHike();
    }
}
