package com.example.crud_java.application.item.exception;

import com.example.crud_java.application.common.exception.BusinessException;

public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
