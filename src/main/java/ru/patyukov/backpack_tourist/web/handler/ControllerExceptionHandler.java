package ru.patyukov.backpack_tourist.web.handler;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.security.SecurityContext;

@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler {

    private final SecurityContext securityContext;

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(@NonNull final BadRequestException exc, Model model) {
        String msg = exc.getMessage();
        String[] msgSplit = msg.split("\\s");

        if ("login".equals(msgSplit[0])) {
            securityContext.setLoginUser("");
            securityContext.setPassword("");
            model.addAttribute("msg", "Не верно указан логин или пароль");
        }

        if ("addUser".equals(msgSplit[0])) {
            securityContext.setLoginUser("");
            securityContext.setPassword("");
            model.addAttribute("msg", "Логин занят");
        }

        if ("user".equals(msgSplit[0])) {
            model.addAttribute("msg", "Поход не найден");
        }

        return msgSplit[1];
    }
}
