package ru.patyukov.backpack_tourist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.patyukov.backpack_tourist.model.Hike;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.service.HikeService;

@Controller
@RequestMapping("/hike")
@SessionAttributes("userLogPasRequest")
public class HikeController {

    @Autowired
    private HikeService hikeService;

    @RequestMapping
    public String viewHike(UserLogPasRequest userLogPasRequest) {
        if (userLogPasRequest.getLogin() == null) return "redirect:/login";
        return "hike";
    }

    @ModelAttribute
    public Hike addHikeModel(UserLogPasRequest userLogPasRequest) {
        if (userLogPasRequest.getLogin() == null) return new Hike();
        else {
            Hike hike = hikeService.findById(userLogPasRequest.getId(), userLogPasRequest.getLogin());   //TODO тут нужно сделать проверку
            return hike;
        }
    }
}
