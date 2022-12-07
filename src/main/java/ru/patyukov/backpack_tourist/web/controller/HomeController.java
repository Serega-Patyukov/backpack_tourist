package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.constant.WebConstant;
import ru.patyukov.backpack_tourist.web.request.UserRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(WebConstant.VERSION_URL + "/home")
@SessionAttributes("userRequest")
public class HomeController {

    private final Facade facade;

    @GetMapping
    public String viewHome() {
        log.info("=========================================================================================");
        return "home";
    }

    @ModelAttribute
    public UserRequest addModelUserRequest(
            @RequestParam(required = false, defaultValue = "") String msg,
            Model model) {

        model.addAttribute("VERSION_URL", WebConstant.VERSION_URL);

        if (!msg.isEmpty() && !(
                msg.equals("Введите учетные данные корректно")
                || msg.equals("Логин занят")
                || msg.equals("reg")
                || msg.equals("error"))) {
            model.addAttribute("msg", "");
        } else {
            model.addAttribute("msg", msg);
        }

        return new UserRequest();
    }

    @PostMapping
    public String saveUser(
            @Valid UserRequest userRequest,
            Errors errors,
            SessionStatus sessionStatus,
            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("msg", "Введите учетные данные корректно");
            return "home";
        }

        facade.saveUser(userRequest);
        sessionStatus.setComplete();
        return "redirect:" + WebConstant.VERSION_URL + "/home";
    }
}
