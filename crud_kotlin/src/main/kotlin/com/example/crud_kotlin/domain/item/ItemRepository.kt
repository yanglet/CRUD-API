package com.example.crud_kotlin.domain.item

interface ItemRepository {
    fun save(item: Item): Long
    fun findByIdOrNull(itemNo: Long): Item?
}