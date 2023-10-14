package com.bank.service.handler;

import com.bank.domain.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler
    public ResponseStatusException ItemNotFoundException(ItemNotFoundException exception,
                                                HttpServletRequest request) {
        return new ResponseStatusException(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseStatusException EntityNotAvailableException() {
//        return null;
//    }
}