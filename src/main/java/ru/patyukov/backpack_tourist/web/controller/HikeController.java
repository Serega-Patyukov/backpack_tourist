package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.constant.WebConstant;
import ru.patyukov.backpack_tourist.web.request.EquipmentRequest;
import ru.patyukov.backpack_tourist.web.response.EquipmentResponse;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(WebConstant.VERSION_URL + "/hike")
public class HikeController {

    private final Facade facade;

    @GetMapping
    public String viewHike() {
        return "hike";
    }

    @ModelAttribute
    public void addModel(Model model,
                         @RequestParam(required = false, defaultValue = "") String msg,
                         @RequestParam(required = false, defaultValue = "0") Long idHike,
                         @RequestParam(required = false, defaultValue = "0") Long idEquipment) {

        model.addAttribute("VERSION_URL", WebConstant.VERSION_URL);

        HikeResponse hikeResponse = facade.getHikeResponse(idHike);
        model.addAttribute("hikeResponse", hikeResponse);
        model.addAttribute("listGroupEquipment", hikeResponse.getEquipmentList().stream()
                .collect(Collectors.groupingBy(EquipmentResponse::getGrp))
                .values().stream()
                .collect(Collectors.toList()));

        if (!msg.isEmpty() && !(
                msg.equals("addEquipment")
                        || msg.equals("Введите данные корректно")
                        || msg.equals("editEquipment")
                        || msg.equals("Измените данные корректно"))) {
            model.addAttribute("msg", "");
        } else {
            model.addAttribute("msg", msg);
        }

        if (msg.equals("addEquipment")) {
            EquipmentRequest equipmentRequest = new EquipmentRequest();
            equipmentRequest.setIdHike(idHike);
            model.addAttribute("equipmentRequest", equipmentRequest);
        }


        if (msg.equals("editEquipment")) {
            model.addAttribute("equipmentRequest", facade.getEquipmentRequest(idEquipment));
        }
    }

    @PostMapping("/deleteEquipment")
    public String deleteEquipment(Long idHike, Long idEquipment) {
        facade.deleteEquipment(idEquipment);
        return "redirect:" + WebConstant.VERSION_URL + "/hike?idHike=" + idHike;
    }

    @PostMapping("/editEquipment")
    public String editEquipment(@Valid EquipmentRequest equipmentRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Измените данные корректно");
            return "hike";
        }
        facade.saveEquipment(equipmentRequest);
        return "redirect:" + WebConstant.VERSION_URL + "/hike?idHike=" + equipmentRequest.getIdHike();
    }

    @PostMapping("/addEquipment")
    public String saveEquipment(@Valid EquipmentRequest equipmentRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите данные корректно");
            return "hike";
        }
        facade.saveEquipment(equipmentRequest);
        return "redirect:" + WebConstant.VERSION_URL + "/hike?idHike=" + equipmentRequest.getIdHike();
    }
}