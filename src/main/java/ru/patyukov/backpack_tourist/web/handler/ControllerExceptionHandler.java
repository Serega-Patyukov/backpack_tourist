package ru.patyukov.backpack_tourist.web.handler;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.patyukov.backpack_tourist.exception.BadRequestException;
import ru.patyukov.backpack_tourist.exception.ForbiddenException;
import ru.patyukov.backpack_tourist.exception.NotFoundException;

@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public String handlerBadRequestException(@NonNull final BadRequestException exc, Model model) {
        String msg = exc.getMessage();
        String[] msgSplit = msg.split("\\s");

        if ("addUser".equals(msgSplit[0])) {
            model.addAttribute("msg", "Логин занят");
        }

        return msgSplit[1];
    }


    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public String handlerNotFoundException(@NonNull final NotFoundException exc) {
        return exc.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    public String handlerForbiddenException(@NonNull final ForbiddenException exc) {
        return exc.getMessage();
    }
}
