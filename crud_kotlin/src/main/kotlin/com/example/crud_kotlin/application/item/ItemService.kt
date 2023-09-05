package com.example.crud_kotlin.application.item

import com.example.crud_kotlin.application.item.dto.ItemCreateRequest
import com.example.crud_kotlin.application.item.dto.ItemReadResponse

interface ItemService {
    fun createItem(request: ItemCreateRequest): Long
    fun readItem(itemNo: Long): ItemReadResponse
}