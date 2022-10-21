package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.web.response.UserResponse;

@Controller
@RequestMapping("/user")
@SessionAttributes("userLogPasRequest")
@AllArgsConstructor
public class UserController {

    private final Facade facade;

    @GetMapping
    public String viewUser() {
        return "user";
    }

    @GetMapping("/exit")
    public String userExit(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @ModelAttribute
    public UserResponse addUserModel(UserLogPasRequest userLogPasRequest) {
        return facade.addUserModel(userLogPasRequest);
    }

    @GetMapping("/hike")
    public String openHike(UserLogPasRequest userLogPasRequest, long id) {
//        if (userLogPasRequest.getLogin() == null) {
//            return "redirect:/user";
//        } else if (!hikeService.existsById(id, userLogPasRequest.getLogin())) {
//            return "redirect:/user";
//        }
//        userLogPasRequest.setId(id);
        return "redirect:/hike";
    }
}
