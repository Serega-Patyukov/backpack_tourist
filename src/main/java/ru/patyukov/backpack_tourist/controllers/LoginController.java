package ru.patyukov.backpack_tourist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.model.UserLogPas;
import ru.patyukov.backpack_tourist.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@SessionAttributes("userLogPas")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewLogin() {
        return "login";
    }

    @ModelAttribute(name = "userLogPas")
    public UserLogPas addUserLogPasModel() {
        return new UserLogPas();
    }

    @PostMapping
    public String login(@Valid UserLogPas userLogPas, Errors errors) {

        if (errors.hasErrors()) return "login";

        if (userService.existsById(userLogPas.getLogin())) {
            User u = userService.findById(userLogPas.getLogin());
            if (userLogPas.getPassword().equals(u.getPassword())) {
                userLogPas.setPassword("");
                return "redirect:/user";
            }
        }

        userLogPas.setError(true);

        return "login";
    }
}
