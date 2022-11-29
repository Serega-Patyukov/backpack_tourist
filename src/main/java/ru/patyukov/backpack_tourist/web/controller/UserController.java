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
                         @RequestParam(required = false, defaultValue = "0") Long idHike,
                         Model model) {

        model.addAttribute("userResponse", facade.getUserResponse());   // модель пользователя
        if ("editUser".equals(msg)) model.addAttribute("userRequest", facade.getUserRequest());   // модель для редактирования пользователя
        if ("addHike".equals(msg)) model.addAttribute("hikeRequest", new HikeRequest());   // модель для редактирования похода
        if (idHike > 0) model.addAttribute("hikeRequest", facade.getHikeRequest(idHike));   // модель похода


        if (!msg.isEmpty() && !(
                "editUser".equals(msg)
                || "Введите учетные данные корректно".equals(msg)
                || "addHike".equals(msg)
                || "Введите данные о походе корректно".equals(msg)
                || "editHike".equals(msg)
                || "Измените данные о походе корректно".equals(msg))) {
            model.addAttribute("msg", "");
        } else {
            model.addAttribute("msg", msg);
        }
    }

    @PostMapping("/deleteUser")
    public String deleteUser() {
        facade.deleteUser();
        return "redirect:/home";
    }

    @PostMapping("/editUser")
    public String updateUser(@Valid UserRequest userRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите данные о походе корректно");
            return "user";
        }
        facade.updateUser(userRequest);
        return "redirect:/user";
    }

    @PostMapping("/addHike")
    public String saveHike(@Valid HikeRequest hikeRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите данные корректно");
            return "user";
        }
        facade.saveHike(hikeRequest);
        return "redirect:/user";
    }

    @PostMapping("/deleteHike")
    public String deleteHike(Long idHike) {
        facade.deleteHike(idHike);
        return "redirect:/user";
    }

    @PostMapping("/editHike")
    public String editHike(@Valid HikeRequest hikeRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("idHike", hikeRequest.getId());
            model.addAttribute("msg", "Измените данные о походе корректно");
            return "user";
        }

        facade.saveHike(hikeRequest);
        return "redirect:/user";
    }
}