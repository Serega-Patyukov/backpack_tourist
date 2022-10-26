package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@SessionAttributes("userLogPasRequest")
@AllArgsConstructor
public class LoginController {

    private final Facade facade;

    @GetMapping
    public String viewLogin(@RequestParam(required = false, defaultValue = "") String msg,
                            Model model) {
        model.addAttribute("msg", msg);
        return "login";
    }

    @ModelAttribute
    public UserLogPasRequest addUserLogPasModel() {
        return new UserLogPasRequest();
    }

    @PostMapping
    public String login(@Valid UserLogPasRequest userLogPasRequest, Errors errors) {
        if (errors.hasErrors()) return "login";
        facade.login(userLogPasRequest);
        return "redirect:/user";
    }
}
