package com.bank.domain.exception;

public class CannotBeCreatedException extends RuntimeException {
    public CannotBeCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}