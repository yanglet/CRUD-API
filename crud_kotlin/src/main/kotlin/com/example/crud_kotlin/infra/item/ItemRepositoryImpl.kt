package com.example.crud_kotlin.infra.item

import com.example.crud_kotlin.domain.item.Item
import com.example.crud_kotlin.domain.item.ItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ItemRepositoryImpl(
    private val itemJpaRepository: ItemJpaRepository
) : ItemRepository {
    override fun save(item: Item): Long = itemJpaRepository.save(item).itemNo

    override fun findByIdOrNull(itemNo: Long): Item? = itemJpaRepository.findByIdOrNull(itemNo)
}