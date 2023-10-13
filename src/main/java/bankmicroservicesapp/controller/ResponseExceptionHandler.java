package bankmicroservicesapp.controller;


import bankmicroservicesapp.exeption.CreateAccountControllerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreateAccountControllerException.class)
    public ResponseEntity<ErrorResponse> handlerCreateAccountControllerException(CreateAccountControllerException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.getMessage()).build();
        return ResponseEntity.ofNullable(errorResponse);
    }
}
