package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.security.SecurityContext;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final Facade facade;
    private final SecurityContext securityContext;

    @GetMapping
    public String viewUser(@RequestParam(required = false, defaultValue = "") String msg,
                           Model model) {
        model.addAttribute("msg", msg);
        model.addAttribute("userResponse", facade.addUserModel());
        return "user";
    }

    @GetMapping("/exit")
    public String userExit() {
        securityContext.setLoginUser("");
        securityContext.setPassword("");
        return "redirect:/";
    }

    @PostMapping
    public String deleteHike(Long idHike) {
        facade.deleteHike(idHike);
        return "redirect:/user";
    }
}
