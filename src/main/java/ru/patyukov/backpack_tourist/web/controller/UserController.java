package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.security.SecurityContext;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final Facade facade;
    private final SecurityContext securityContext;

    @GetMapping
    public String viewUser() {
        return "user";
    }

    @ModelAttribute
    public void addModel(@RequestParam(required = false, defaultValue = "") String msg,
                         @RequestParam(required = false, defaultValue = "") String addHike,
                         Model model) {
        model.addAttribute("msg", msg);
        model.addAttribute("addHike", addHike);
        model.addAttribute("userResponse", facade.getUserResponse());
        model.addAttribute("hikeRequest", new HikeRequest());
    }

    @GetMapping("/exit")
    public String userExit() {
        securityContext.setLoginUser("");
        securityContext.setPassword("");
        return "redirect:/home";
    }

    @PostMapping
    public String deleteHike(Long idHike) {

        //todo проверить наличие этого похода у туриста

        facade.deleteHike(idHike);
        return "redirect:/user";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(String login) {
        facade.deleteUser(login);
        return "redirect:/";
    }

    @PostMapping("/addHike")
    public String saveHike(@Valid HikeRequest hikeRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите учетные данные корректно");
            model.addAttribute("addHike", "add");
            return "user";
        }
        facade.saveHike(hikeRequest);
        return "redirect:/user";
    }
}
