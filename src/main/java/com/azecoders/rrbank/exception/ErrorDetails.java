package com.azecoders.rrbank.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;
@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ErrorDetails {
    String message;
    LocalDateTime timeStamp;
    HttpStatus httpStatus;
}
