package ru.patyukov.backpack_tourist.web.handler;

import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.patyukov.backpack_tourist.exception.BadRequestException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(@NonNull final BadRequestException exc, Model model) {
        String msg = exc.getMessage();
        String[] msgSplit = msg.split("\\s");

        if ("login".equals(msgSplit[0])) {
            model.addAttribute("msg", "Не верно указан логин или пароль");
        }

        if ("addUser".equals(msgSplit[0])) {
            model.addAttribute("msg", "Логин занят");
        }

        return msgSplit[1];
    }
}
