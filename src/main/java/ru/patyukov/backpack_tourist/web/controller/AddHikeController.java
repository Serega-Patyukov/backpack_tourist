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
@AllArgsConstructor
@RequestMapping("/addHike")
public class AddHikeController {

    private final Facade facade;

    @GetMapping
    public String viewAddHike() {
        return "addHike";
    }

    @ModelAttribute
    public void addModel(Model model) {

        //todo тут нужно проверить аутентифицированного пользователя

        model.addAttribute("hikeRequest", new HikeRequest());
    }

    @PostMapping
    public String saveHike(@Valid HikeRequest hikeRequest, Errors errors) {
        if (errors.hasErrors()) return "addHike";
        facade.saveHike(hikeRequest);
        return "redirect:/user";
    }
}
