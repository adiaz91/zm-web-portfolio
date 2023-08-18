package com.portfolio.exceptions.handlers;


import com.portfolio.exceptions.InvalidEntityException;
import com.portfolio.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice("com.portfolio.controllers.api")
@Slf4j
public class RestControllerExceptionHandler {

    private static final String MESSAGE="message";
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String,String> resourceNotFoundException(ResourceNotFoundException ex) {
        log.info(ex.getMessage(),ex);
        return Map.of(MESSAGE, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> exception(ResourceNotFoundException ex) {
        log.error(ex.getMessage(),ex);
        return Map.of(MESSAGE, "An internal server error has occurred");
    }

    @ExceptionHandler(value = {InvalidEntityException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,String> resourceNotFoundException(InvalidEntityException ex) {
        log.info(ex.getMessage(),ex);
        return Map.of(MESSAGE, ex.getMessage());

    }

}
