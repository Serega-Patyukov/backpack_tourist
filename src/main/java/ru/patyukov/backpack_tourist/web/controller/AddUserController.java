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
@RequestMapping("/addUser")
@AllArgsConstructor
@SessionAttributes("userRequest")
public class AddUserController {

    private final Facade facade;

    @GetMapping
    public String viewAddUser(@RequestParam(required = false, defaultValue = "") String msg,
                              Model model) {
        model.addAttribute("msg", msg);
        return "addUser";
    }

    @ModelAttribute
    public UserRequest addUserModel() {
        return new UserRequest();
    }

    @PostMapping
    public String addUser(@Valid UserRequest userRequest, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) return "addUser";
        facade.addUser(userRequest);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
