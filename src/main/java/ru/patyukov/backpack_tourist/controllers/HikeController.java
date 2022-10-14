package ru.patyukov.backpack_tourist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.patyukov.backpack_tourist.model.Hike;
import ru.patyukov.backpack_tourist.model.User;
import ru.patyukov.backpack_tourist.model.UserLogPas;
import ru.patyukov.backpack_tourist.services.HikeService;

@Controller
@RequestMapping("/hike")
@SessionAttributes("userLogPas")
public class HikeController {

    @Autowired
    private HikeService hikeService;

    @RequestMapping
    public String viewHike(UserLogPas userLogPas) {
        if (userLogPas.getLogin() == null) return "redirect:/login";
        return "hike";
    }

    @ModelAttribute(name = "hike")
    public Hike addHikeModel(UserLogPas userLogPas) {
        if (userLogPas.getLogin() == null) return new Hike();
        else {
            Hike hike = hikeService.findById(userLogPas.getId(), userLogPas.getLogin());   //TODO тут нужно сделать проверку
            return hike;
        }
    }
}
