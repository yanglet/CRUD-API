package com.example.crud_kotlin.infra.item

import com.example.crud_kotlin.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemJpaRepository : JpaRepository<Item, Long> {
}