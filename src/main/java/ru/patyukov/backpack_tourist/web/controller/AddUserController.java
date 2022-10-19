package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.UserRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/addUser")
@AllArgsConstructor
public class AddUserController {

    private final Facade facade;

    @GetMapping
    public String viewAddUser() {
        return "addUser";
    }

    @ModelAttribute
    public UserRequest addUserModel() {
        return new UserRequest();
    }

    @PostMapping
    public String addUser(@Valid UserRequest userRequest, Errors errors) {
        if (errors.hasErrors()) return "addUser";
        return facade.addUser(userRequest);
    }
}
