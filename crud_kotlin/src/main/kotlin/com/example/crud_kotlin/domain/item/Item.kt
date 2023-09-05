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
}