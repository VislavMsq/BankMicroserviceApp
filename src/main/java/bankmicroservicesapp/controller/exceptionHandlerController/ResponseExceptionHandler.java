package bankmicroservicesapp.controller.exceptionHandlerController;


import bankmicroservicesapp.exeption.CreateAccountControllerException;

import bankmicroservicesapp.exeption.InvalidIdException;
import bankmicroservicesapp.exeption.InvalidStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CreateAccountControllerException.class, InvalidStatusException.class})
    public ResponseEntity<ErrorResponse> badRequestExceptionHandler(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder(
                        ex, HttpStatus.BAD_REQUEST,
                        ex.getMessage())
                .build();
        return ResponseEntity.ofNullable(errorResponse);
    }

    @ExceptionHandler({InvalidIdException.class})
    public ResponseEntity<ErrorResponse> notFoundExceptionHandler(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder(
                        ex, HttpStatus.NOT_FOUND,
                        ex.getMessage())
                .build();
        return ResponseEntity.ofNullable(errorResponse);
    }
}