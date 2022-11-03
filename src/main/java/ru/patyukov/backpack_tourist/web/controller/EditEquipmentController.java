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
@RequestMapping("/editEquipment")
public class EditEquipmentController {

    private final Facade facade;

    @GetMapping
    public String viewEditEquipment() {
        return "editEquipment";
    }

    @ModelAttribute
    public void addModel(Model model, Long idEquipment) {

        //todo тут нужно проверить аутентифицированного пользователя и есть ли у него поход и снаряжение с указанными id.

        model.addAttribute("equipmentRequest", facade.getEquipmentRequest(idEquipment));
    }

    @PostMapping
    public String saveEquipment(@Valid EquipmentRequest equipmentRequest, Errors errors) {
        if (errors.hasErrors()) return "editEquipment";
        facade.saveEquipment(equipmentRequest);
        return "redirect:/hike?idHike=" + equipmentRequest.getIdHike();
    }
}
