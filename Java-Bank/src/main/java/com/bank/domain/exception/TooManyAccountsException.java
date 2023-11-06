package com.bank.domain.exception;

public class TooManyAccountsException extends RuntimeException {

    public TooManyAccountsException(String message) {
        super(message);
    }
}
