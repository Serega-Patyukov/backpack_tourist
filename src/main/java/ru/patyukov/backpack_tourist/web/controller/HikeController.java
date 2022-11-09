package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/hike")
public class HikeController {

    private final Facade facade;

    @GetMapping
    public String viewHike() {
        return "hike";
    }

    @ModelAttribute
    public void addModel(Model model,
                         Long idHike,
                         @RequestParam(required = false, defaultValue = "0") Long editEquipment) {

        if (editEquipment > 0) model.addAttribute("equipmentRequest", facade.getEquipmentRequest(editEquipment));
        HikeResponse hikeResponse = facade.getHikeResponse(idHike);
        model.addAttribute("editEquipment", editEquipment);
        model.addAttribute("hikeResponse", hikeResponse);
        model.addAttribute("listGroupEquipment", hikeResponse.getEquipmentList().stream()
                .collect(Collectors.groupingBy(EquipmentResponse::getGrp))
                .values().stream()
                .collect(Collectors.toList()));
    }

    @PostMapping
    public String deleteEquipment(Long idHike, Long idEquipment) {
        facade.deleteEquipment(idEquipment);
        return "redirect:/hike?idHike=" + idHike;
    }

    @PostMapping("/editEquipment")
    public String saveEquipment(@Valid EquipmentRequest equipmentRequest, Errors errors) {
        if (errors.hasErrors()) return "hike";
        facade.saveEquipment(equipmentRequest);
        return "redirect:/hike?idHike=" + equipmentRequest.getIdHike();
    }
}
