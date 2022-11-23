package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.HikeRequest;
import ru.patyukov.backpack_tourist.web.request.UserRequest;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final Facade facade;

    @GetMapping
    public String viewUser() {
        return "user";
    }

    @ModelAttribute
    public void addModel(@RequestParam(required = false, defaultValue = "") String msg,
                         @RequestParam(required = false, defaultValue = "") String addHike,
                         @RequestParam(required = false, defaultValue = "") String login,
                         @RequestParam(required = false, defaultValue = "0") Long idHike,
                         @RequestParam(required = false, defaultValue = "0") Long editHike,
                         Model model) {
        model.addAttribute("msg", msg);
        model.addAttribute("addHike", addHike);
        model.addAttribute("userResponse", facade.getUserResponse());
        model.addAttribute("hikeRequest", new HikeRequest());
        if (!login.isEmpty()) {
            model.addAttribute("userRequest", facade.getUserRequest(login));
            model.addAttribute("login", login);
        } else {
            model.addAttribute("login", login);
        }
        if (idHike > 0) {
            model.addAttribute("hikeRequest", facade.getHikeRequest(idHike));
        }
        model.addAttribute("editHike", editHike);
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
        return "redirect:/home";
    }

    @PostMapping("/addHike")
    public String saveHike(@Valid HikeRequest hikeRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите данные корректно");
            model.addAttribute("addHike", "add");
            return "user";
        }
        facade.saveHike(hikeRequest);
        return "redirect:/user";
    }

    @PostMapping("/editUser")
    public String updateUser(@Valid UserRequest userRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите учетные данные корректно");
            model.addAttribute("login", userRequest.getLogin());
            return "user";
        }
        facade.updateUser(userRequest);
        return "redirect:/user";
    }

    @PostMapping("/editHike")
    public String editHike(@Valid HikeRequest hikeRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("editHike", hikeRequest.getId());
            model.addAttribute("msg", "Введите данные корректно");
            return "user";
        }
        facade.saveHike(hikeRequest);
        return "redirect:/user";
    }
}
