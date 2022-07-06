package ru.patyukov.backpack_tourist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/addUser")
public class AddUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewAddUser() {
        return "addUser";
    }

    @ModelAttribute(name = "user")
    public User addUserModel() {
        return new User();
    }

    @PostMapping
    public String addUser(@Valid User user, Errors errors) {

        if (errors.hasErrors()) return "addUser";

        if (userService.existsById(user.getLogin())) {
            user.setLoginIsEmpty(true);
            return "addUser";
        }

        userService.save(user);

        return "redirect:/";
    }

}
