package com.azecoders.rrbank.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
public class AccountFoundException extends RuntimeException {
    private final HttpStatus status;
    public AccountFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
