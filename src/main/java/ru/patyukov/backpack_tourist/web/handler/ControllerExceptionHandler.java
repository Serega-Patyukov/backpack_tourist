package ru.patyukov.backpack_tourist.web.handler;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.patyukov.backpack_tourist.exception.BadRequestException;

@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(@NonNull final BadRequestException exc, Model model) {
        String msg = exc.getMessage();
        String[] msgSplit = msg.split("\\s");

        if ("addUser".equals(msgSplit[0])) {
            model.addAttribute("msg", "Логин занят");
        }

        if ("404".equals(msgSplit[0])) return "404";
        if ("403".equals(msgSplit[0])) return "403";

        return msgSplit[1];
    }
}
