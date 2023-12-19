package com.example.crud_kotlin.presentation.global

import com.example.crud_kotlin.application.common.exception.BusinessException
import com.example.crud_kotlin.application.common.log.Log
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler : Log {

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<ExceptionResponse> {
        log.error("BusinessException", e)
        return ResponseEntity.status(BAD_REQUEST).body(
            ExceptionResponse(e.message)
        )
    }

    @ExceptionHandler(JsonParseException::class)
    protected fun handleJsonParseException(e: JsonParseException): ResponseEntity<ExceptionResponse> {
        log.error("JsonParseException", e)
        return ResponseEntity.status(BAD_REQUEST).body(
            ExceptionResponse(e.message)
        )
    }

    @ExceptionHandler(Exception::class)
    protected fun handleJsonParseException(e: Exception): ResponseEntity<ExceptionResponse> {
        log.error("Exception", e)
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
            ExceptionResponse(e.message)
        )
    }
}