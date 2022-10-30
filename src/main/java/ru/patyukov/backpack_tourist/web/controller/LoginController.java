package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.security.SecurityContext;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
@SessionAttributes("userLogPasRequest")
public class LoginController {

    private final Facade facade;
    private final SecurityContext securityContext;

    @GetMapping
    public String viewLogin(@RequestParam(required = false, defaultValue = "Введите логин и пароль") String msg,
                            Model model) {
        model.addAttribute("msg", msg);
        return "login";
    }

    @ModelAttribute
    public UserLogPasRequest addUserLogPasRequestModel() {
        return new UserLogPasRequest();
    }

    @PostMapping
    public String login(@Valid UserLogPasRequest userLogPasRequest,
                        Errors errors, Model model, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите корректно логин и пароль");
            return "login";
        }
        securityContext.setLoginUser(userLogPasRequest.getLogin());
        securityContext.setPassword(userLogPasRequest.getPassword());
        facade.login();
        sessionStatus.setComplete();
        return "redirect:/user";
    }
}
