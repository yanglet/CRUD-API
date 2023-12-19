package com.example.crud_kotlin.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    lateinit var insertDate: LocalDateTime
        protected set

    @CreatedBy
    @Column(updatable = false)
    lateinit var insertOperator: String
        protected set

    @LastModifiedDate
    lateinit var updateDate: LocalDateTime
        protected set

    @LastModifiedBy
    lateinit var updateOperator: String
        protected set
}