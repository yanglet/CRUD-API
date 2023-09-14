package com.example.crud_kotlin.domain.item

import com.example.crud_kotlin.domain.common.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Table(name = "ITEM")
@Where(clause = "deleted = false")
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var itemNo: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var quantity: Long,

    @Column(nullable = false)
    var deleted: Boolean = false,

    var deletedDate: LocalDateTime? = null

) : BaseEntity() {

    fun update(name: String, quantity: Long) {
        this.name = name
        this.quantity = quantity
    }

    fun updateQuantity(type: String, quantity: Long) {
        when (type) {
            "ADD" -> this.quantity = this.quantity + quantity
            "MINUS" -> this.quantity = this.quantity - quantity
        }
    }

    fun delete() {
        this.deleted = true
        this.deletedDate = LocalDateTime.now()
    }
}