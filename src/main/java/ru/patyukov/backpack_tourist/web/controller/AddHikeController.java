package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.model.Hike;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.service.HikeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/addHike")
@SessionAttributes("userLogPasRequest")
@AllArgsConstructor
public class AddHikeController {

    private final Facade facade;

    @GetMapping
    public String addViewHike(UserLogPasRequest userLogPasRequest) {
        if (userLogPasRequest.getLogin() == null) return "redirect:/login";
        return "addHike";
    }

    @ModelAttribute(name = "hike")
    public Hike addHikeModel() {
        return new Hike();
    }

    @PostMapping
    public String addHike(@Valid Hike hike, Errors errors, UserLogPasRequest userLogPasRequest) {

        if (errors.hasErrors()) return "addHike";

//        hikeService.save(hike, userLogPasRequest.getLogin());
        return "redirect:/user";
    }
}
