package com.example.crud_kotlin.application.item.dto

import java.time.LocalDateTime

data class ItemReadResponse(
    val itemNo: Long,
    val name: String,
    val quantity: Long,
    val deleted: Boolean,
    val deletedDate: LocalDateTime?
)