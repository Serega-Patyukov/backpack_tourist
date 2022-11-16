package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.UserRequest;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/editUser")
public class EditUserController {

    private final Facade facade;

    @GetMapping
    public String viewEditUser() {
        return "editUser";
    }

    @ModelAttribute
    public UserRequest addModel(String login) {
        return facade.getUserRequest(login);
    }

    @PostMapping("/editUser")
    public String updateUser(@Valid UserRequest userRequest, Errors errors) {
        if (errors.hasErrors()) return "editUser";
        facade.updateUser(userRequest);
        return "redirect:/user";
    }
}
