package com.bankmicroservicesapp.exception;

public class DataNotExistException extends RuntimeException {
    public DataNotExistException(String userNotExist) {
        super(userNotExist);
    }
}
