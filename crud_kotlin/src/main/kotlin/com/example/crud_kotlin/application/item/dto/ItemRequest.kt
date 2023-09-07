package com.example.crud_kotlin.application.item.dto

import java.time.LocalDateTime

data class ItemReadRequest(
    val keyword: String?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?
)

data class ItemCreateRequest(
    val name: String,
    val quantity: Long
)
