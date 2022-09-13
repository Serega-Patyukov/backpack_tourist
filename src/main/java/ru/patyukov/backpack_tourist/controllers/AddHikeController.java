package ru.patyukov.backpack_tourist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.model.Hike;
import ru.patyukov.backpack_tourist.model.UserLogPas;
import ru.patyukov.backpack_tourist.services.HikeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/addHike")
@SessionAttributes("userLogPas")
public class AddHikeController {

    @Autowired
    private HikeService hikeService;

    @GetMapping
    public String addViewHike(UserLogPas userLogPas) {
        if (userLogPas.getLogin() == null) return "redirect:/login";
        return "addHike";
    }

    @ModelAttribute(name = "hike")
    public Hike addHikeModel() {
        return new Hike();
    }

    @PostMapping
    public String addHike(@Valid Hike hike, Errors errors, UserLogPas userLogPas) {

        if (errors.hasErrors()) return "addHike";

        hikeService.save(hike, userLogPas);
        return "redirect:/user";
    }
}
