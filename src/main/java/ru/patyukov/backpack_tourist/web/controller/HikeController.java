package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;

@Controller
@RequestMapping("/hike")
@AllArgsConstructor
public class HikeController {

    private final Facade facade;

    @GetMapping
    public String viewHike(Model model,
                           @RequestParam(required = false, defaultValue = "0") Long idHike) {
        model.addAttribute("hikeResponse", facade.addHikeModel(idHike));
        return "hike";
    }
}
