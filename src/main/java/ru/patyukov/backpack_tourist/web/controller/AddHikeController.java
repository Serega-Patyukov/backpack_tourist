package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/addHike")
@AllArgsConstructor
public class AddHikeController {

    private final Facade facade;

    @GetMapping
    public String addViewHike(Model model) {
        model.addAttribute("hikeRequest", facade.addHikeModel());
        return "addHike";
    }

    @PostMapping
    public String addHike(@Valid HikeRequest hikeRequest, Errors errors) {
        if (errors.hasErrors()) return "addHike";
        facade.addHike(hikeRequest);
        return "redirect:/user";
    }
}
