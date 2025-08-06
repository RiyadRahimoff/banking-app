package com.azecoders.rrbank.exception;

import lombok.Getter;

@Getter
public class UserFoundException extends RuntimeException {
    private String code;
    public UserFoundException(String message,String code) {
        super(message);
        this.code=code;
    }
}
