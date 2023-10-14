package com.bank.service.handler;

import com.bank.domain.exception.CannotBeCreatedException;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.domain.exception.NotEnoughFundsException;
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
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }

    @ExceptionHandler
    public ResponseStatusException CannotBeCreatedException(CannotBeCreatedException exception,
                                                            HttpServletRequest request) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    }

    @ExceptionHandler
    public ResponseStatusException EntityNotAvailableException(EntityNotAvailableException exception,
                                                               HttpServletRequest request) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }

    @ExceptionHandler
    public ResponseStatusException NotEnoughFundsException(NotEnoughFundsException exception,
                                                           HttpServletRequest request) {
        return  new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage(), exception);
    }
}