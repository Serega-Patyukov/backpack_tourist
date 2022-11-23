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

        if ("login".equals(msgSplit[0])) {
            model.addAttribute("msg", "Логин или пароль указан не верно");
        }

        if ("addUser".equals(msgSplit[0])) {
            model.addAttribute("reg", "Логин занят");
        }

        if ("user".equals(msgSplit[0])) {
            model.addAttribute("msg", "Поход не найден");
        }

        return msgSplit[1];
    }
}
