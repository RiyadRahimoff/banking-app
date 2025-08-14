package com.azecoders.rrbank.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFound(UserFoundException ex) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getStatus()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    public ResponseEntity<ErrorDetails> handlePasswordSame(IllegalArgumentException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

}
