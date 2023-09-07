package com.example.crud_kotlin.application.common.dto

data class PageDto<T>(
    val content: T,
    val count: Long
)
