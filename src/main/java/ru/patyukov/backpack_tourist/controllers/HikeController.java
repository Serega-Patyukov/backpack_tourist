package ru.patyukov.backpack_tourist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hike")
public class HikeController {

    @GetMapping
    public String viewHike() {
        return "hike";
    }

}
