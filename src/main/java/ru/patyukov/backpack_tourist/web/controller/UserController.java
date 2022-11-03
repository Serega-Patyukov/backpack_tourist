package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.security.SecurityContext;

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
                         Model model) {
        model.addAttribute("msg", msg);
        model.addAttribute("userResponse", facade.getUserResponse());
    }

    @GetMapping("/exit")
    public String userExit() {
        securityContext.setLoginUser("");
        securityContext.setPassword("");
        return "redirect:/";
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
}
