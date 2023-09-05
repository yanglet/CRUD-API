package com.example.crud_kotlin.application.item.exception

import com.example.crud_kotlin.application.common.exception.BusinessException

class ItemNotFoundException(message: String) : BusinessException(message) {
}