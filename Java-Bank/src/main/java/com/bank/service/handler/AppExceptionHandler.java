package com.bank.service.handler;

import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.domain.exception.NotEnoughFundsException;
import com.bank.domain.exception.TooManyAccountsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity EntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity(new StringBuilder(exception.getClass().getSimpleName()).append(" ")
                .append(exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity EntityNotAvailableException(EntityNotAvailableException exception) {
        return new ResponseEntity(new StringBuilder(exception.getClass().getSimpleName()).append(" ")
                .append(exception.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity NotEnoughFundsException(NotEnoughFundsException exception) {
        return  new ResponseEntity(new StringBuilder(exception.getClass().getSimpleName()).append(" ")
                .append(exception.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity ConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity(exception.getConstraintViolations().stream().map(consViol ->
                consViol.getConstraintDescriptor().getAttributes().get("message")).collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity IllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity TooManyAccountsException(TooManyAccountsException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
