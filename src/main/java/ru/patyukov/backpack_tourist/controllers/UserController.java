package ru.patyukov.backpack_tourist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.model.UserLogPas;
import ru.patyukov.backpack_tourist.services.HikeService;
import ru.patyukov.backpack_tourist.services.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("userLogPas")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HikeService hikeService;

    @GetMapping
    public String viewUser(UserLogPas userLogPas) {
        if (userLogPas.getLogin() == null) return "redirect:/login";
        return "user";
    }

    @GetMapping("/exit")
    public String userExit(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @ModelAttribute(name = "user")
    public User addUserModel(UserLogPas userLogPas) {
        if (userLogPas.getLogin() == null) return new User();
        else {
            User user = userService.findById(userLogPas.getLogin());   //TODO тут нужно сделать проверку
            return user;
        }
    }

    @PostMapping
    public String openHike(UserLogPas userLogPas, long id) {
        if (userLogPas.getLogin() == null) {
            return "redirect:/user";
        } else if (!hikeService.existsById(id, userLogPas.getLogin())) {
            return "redirect:/user";
        }
        userLogPas.setId(id);
        return "redirect:/hike";
    }
}
