package com.example.crud_java.presentation.global;

import com.example.crud_java.application.common.exception.BusinessException;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException e) {
        log.error("BusinessException", e);
        ExceptionResponse response = ExceptionResponse.of(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(JsonParseException.class)
    protected ResponseEntity<ExceptionResponse> handleJsonParseException(JsonParseException e) {
        log.error("JsonParseException", e);
        ExceptionResponse response = ExceptionResponse.of(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("Exception", e);
        ExceptionResponse response = ExceptionResponse.of(e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }
}
