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
@AllArgsConstructor
@RequestMapping("/editHike")
public class EditHikeController {

    private final Facade facade;

    @GetMapping
    public String viewEditHike() {
        return "editHike";
    }

    @ModelAttribute
    public void addModel(Model model, Long idHike) {

        //todo тут нужно проверить аутентифицированного пользователя и есть ли у него поход с указанным id.

        model.addAttribute("hikeRequest", facade.getHikeRequest(idHike));
    }

    @PostMapping
    public String saveHike(@Valid HikeRequest hikeRequest, Errors errors) {
        if (errors.hasErrors()) return "editHike";
        facade.saveHike(hikeRequest);
        return "redirect:/hike?idHike=" + hikeRequest.getId();
    }
}
