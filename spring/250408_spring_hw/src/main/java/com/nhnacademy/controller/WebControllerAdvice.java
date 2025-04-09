package com.nhnacademy.controller;

import com.nhnacademy.exception.StudentAlreadyExistsException;
import com.nhnacademy.exception.StudentNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler({StudentAlreadyExistsException.class,
                        StudentNotFoundException.class,
                        ValidationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(Exception ex, Model model) {
        ModelAndView mav = new ModelAndView("error");
        log.error("resource not found", ex);
        mav.addObject("exception", ex);
        return mav;
    }
}
