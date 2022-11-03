package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/hike")
public class HikeController {

    private final Facade facade;

    @GetMapping
    public String viewHike(Model model,
                           @RequestParam(required = false, defaultValue = "0") Long idHike) {
        HikeResponse hikeResponse = facade.addHikeModel(idHike);
        model.addAttribute("hikeResponse", hikeResponse);
        model.addAttribute("listGroupEquipment", hikeResponse.getEquipmentList().stream()
                .collect(Collectors.groupingBy(EquipmentResponse::getGrp))
                .values().stream()
                .collect(Collectors.toList()));
        return "hike";
    }

    @PostMapping
    public String deleteEquipment(Long idHike, Long idEquipment) {
        facade.deleteEquipment(idEquipment);
        return "redirect:/hike?idHike=" + idHike;
    }
}
