package com.example.crud_kotlin.domain.item

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

interface ItemRepository {
    fun findAllCustom(
        pageable: Pageable,
        keyword: String?,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?
    ): Page<Item>
    fun save(item: Item): Long
    fun saveAndFlush(item: Item): Item
    fun findByIdOrNull(itemNo: Long): Item?
}