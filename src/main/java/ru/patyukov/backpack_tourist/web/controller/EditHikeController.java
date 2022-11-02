package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/editHike")
@AllArgsConstructor
public class EditHikeController {

    private final Facade facade;

    @GetMapping
    public String addViewHike() {
        return "editHike";
    }

    @ModelAttribute
    public void addModel(Model model, Long idHike) {
        model.addAttribute("hikeRequest", facade.editHikeModel(idHike));
    }

    @PostMapping
    public String addHike(@Valid HikeRequest hikeRequest, Errors errors) {
        if (errors.hasErrors()) return "addHike";
        facade.addHike(hikeRequest);
        return "redirect:/hike?idHike=" + hikeRequest.getId();
    }
}
