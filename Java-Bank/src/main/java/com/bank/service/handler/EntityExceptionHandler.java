package com.bank.service.handler;

import com.bank.domain.exception.CannotBeCreatedException;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.domain.exception.NotEnoughFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity ItemNotFoundException(ItemNotFoundException exception,
                                                         HttpServletRequest request) {
        return new ResponseEntity(new StringBuilder(exception.getClass().getSimpleName()).append(" ")
                .append(exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity CannotBeCreatedException(CannotBeCreatedException exception,
                                                            HttpServletRequest request) {
        return new ResponseEntity(new StringBuilder(exception.getClass().getSimpleName()).append(" ")
                .append(exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity EntityNotAvailableException(EntityNotAvailableException exception,
                                                               HttpServletRequest request) {
        return new ResponseEntity(new StringBuilder(exception.getClass().getSimpleName()).append(" ")
                .append(exception.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity NotEnoughFundsException(NotEnoughFundsException exception,
                                                           HttpServletRequest request) {
        return  new ResponseEntity(new StringBuilder(exception.getClass().getSimpleName()).append(" ")
                .append(exception.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
