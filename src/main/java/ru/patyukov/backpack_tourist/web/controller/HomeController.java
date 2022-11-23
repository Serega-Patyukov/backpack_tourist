package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.UserRequest;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/home")
@SessionAttributes("userRequest")
public class HomeController {

    private final Facade facade;

    @GetMapping
    public String viewHome() {
        return "home";
    }

    @ModelAttribute
    public UserRequest addModelUserRequest(@RequestParam(required = false, defaultValue = "") String msg,
                                           @RequestParam(required = false, defaultValue = "") String reg,
                                Model model) {

        if (!msg.isEmpty()  &&
                !(msg.equals("Логин или пароль указан не верно") || msg.equals("Введите учетные данные корректно"))) {
            model.addAttribute("msg", "");
        } else {
            model.addAttribute("msg", msg);
        }

        if (!reg.isEmpty() &&
                !(reg.equals("reg") || reg.equals("Логин занят"))) {
            model.addAttribute("reg", "");
        } else {
            model.addAttribute("reg", reg);
        }

        return new UserRequest();
    }

    @PostMapping("/reg")
    public String saveUser(@Valid UserRequest userRequest, Errors errors, SessionStatus sessionStatus, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите учетные данные корректно");
            model.addAttribute("reg", "reg");
            return "home";
        }
        facade.saveUser(userRequest);
        sessionStatus.setComplete();
        return "redirect:/home";
    }
}
