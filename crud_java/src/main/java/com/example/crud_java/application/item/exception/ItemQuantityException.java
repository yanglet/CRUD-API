package com.example.crud_java.application.item.exception;

import com.example.crud_java.application.common.exception.BusinessException;

public class ItemQuantityException extends BusinessException {
    public ItemQuantityException(String message) {
        super(message);
    }
}
