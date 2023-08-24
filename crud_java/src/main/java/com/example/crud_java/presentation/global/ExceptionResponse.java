package com.example.crud_java.presentation.global;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionResponse {
    private String message;

    public static ExceptionResponse of(String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.message = message;
        return exceptionResponse;
    }
}
