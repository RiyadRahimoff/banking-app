package com.azecoders.rrbank.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionEnums {
    USER_NOT_FOUND("USER_NOT_FOUND","User not found!!!");
    private final String code;
    private final String message;
}
