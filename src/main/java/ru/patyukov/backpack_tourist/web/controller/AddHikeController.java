package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/addHike")
@SessionAttributes("userLogPasRequest")
@AllArgsConstructor
public class AddHikeController {

    private final Facade facade;

    @GetMapping
    public String addViewHike() {
        return "addHike";
    }

    @ModelAttribute
    public HikeRequest addHikeModel(UserLogPasRequest userLogPasRequest) {
        return facade.addHikeModel(userLogPasRequest);
    }

    @PostMapping
    public String addHike(@Valid HikeRequest hikeRequest, Errors errors, UserLogPasRequest userLogPasRequest) {
        if (errors.hasErrors()) return "addHike";
        facade.addHike(hikeRequest, userLogPasRequest);
        return "redirect:/user";
    }
}
