package com.bankmicroservicesapp.exeption;

public class DataNotExistException extends RuntimeException {
    public DataNotExistException(String userNotExist) {
        super(userNotExist);
    }
}
