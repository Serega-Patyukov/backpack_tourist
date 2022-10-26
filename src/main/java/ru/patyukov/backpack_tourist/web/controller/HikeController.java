package ru.patyukov.backpack_tourist.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.backpack_tourist.facade.Facade;
import ru.patyukov.backpack_tourist.web.request.UserLogPasRequest;
import ru.patyukov.backpack_tourist.web.response.HikeResponse;

@Controller
@RequestMapping("/hike")
@SessionAttributes("userLogPasRequest")
@AllArgsConstructor
public class HikeController {

    private final Facade facade;

    @GetMapping
    public String viewHike() {
        return "hike";
    }

    @ModelAttribute
    public HikeResponse addHikeModel(UserLogPasRequest userLogPasRequest,
                                     Long idHike) {
        return facade.addHikeModel(userLogPasRequest, idHike);
    }
}
