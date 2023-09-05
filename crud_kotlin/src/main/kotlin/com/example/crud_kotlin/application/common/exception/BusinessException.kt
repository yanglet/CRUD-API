package com.example.crud_kotlin.application.common.exception

abstract class BusinessException(message: String) : RuntimeException(message) {
}