package com.portfolio.exceptions.handlers;


import com.portfolio.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@ControllerAdvice("com.portfolio.controllers.mvc")
@Slf4j
public class ControllerExceptionHandler {
    private static final String CODE="code";
    private static final String MESSAGE="message";

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView  resourceNotFoundException(ResourceNotFoundException ex) {
        log.info(ex.getMessage(),ex);
        return createModelAndView(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(Exception ex) {
        log.error(ex.getMessage(),ex);
        return createModelAndView(HttpStatus.INTERNAL_SERVER_ERROR,"An internal server error has occurred");
    }

    private ModelAndView createModelAndView(HttpStatus status,String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject(CODE, status.value());
        modelAndView.addObject(MESSAGE,message);
        return modelAndView;
    }
}
