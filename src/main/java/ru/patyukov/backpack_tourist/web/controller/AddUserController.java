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
@RequestMapping("/addUser")
@SessionAttributes("userRequest")
public class AddUserController {

    private final Facade facade;

    @GetMapping
    public String viewAddUser() {
        return "addUser";
    }

    @ModelAttribute
    public UserRequest addModel(@RequestParam(required = false, defaultValue = "") String msg,
                                    Model model) {
        model.addAttribute("msg", msg);
        return new UserRequest();
    }

    @PostMapping
    public String saveUser(@Valid UserRequest userRequest, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) return "addUser";
        facade.saveUser(userRequest);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
