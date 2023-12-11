package com.bankmicroservicesapp.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String e) {
        super(e);
    }
}
