package com.example.crud_java.presentation.global;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionResponse {
    private String message;

    public static ExceptionResponse of(String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.message = message;
        return exceptionResponse;
    }
}
