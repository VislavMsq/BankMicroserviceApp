package com.bankmicroservicesapp.exeption;

public class CreateAccountControllerException extends RuntimeException {
    public CreateAccountControllerException(String message) {
        super(message);
    }
}
