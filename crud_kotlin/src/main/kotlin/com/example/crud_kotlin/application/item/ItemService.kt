package com.example.crud_kotlin.application.item

import com.example.crud_kotlin.application.common.dto.PageDto
import com.example.crud_kotlin.application.item.dto.*
import org.springframework.data.domain.Pageable

interface ItemService {
    fun readItems(pageable: Pageable, request: ItemReadRequest): PageDto<Iterable<ItemReadResponse>>
    fun createItem(request: ItemCreateRequest): Long
    fun readItem(itemNo: Long): ItemReadResponse
    fun updateItem(itemNo: Long, request: ItemUpdateRequest)
    fun updateItemQuantity(itemNo: Long, request: ItemUpdateQuantityRequest)
}