package ru.patyukov.backpack_tourist.web.handler;

import lombok.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.patyukov.backpack_tourist.exception.BadRequestException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(@NonNull final BadRequestException exc) {
        return exc.getMessage();
    }
}
