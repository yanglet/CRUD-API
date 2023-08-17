package com.example.crud_java.application.item.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemQuantityUpdateRequest {
    private String type;
    private Long quantity;

    public static ItemQuantityUpdateRequest of(String type, Long quantity) {
        ItemQuantityUpdateRequest itemQuantityUpdateRequest = new ItemQuantityUpdateRequest();
        itemQuantityUpdateRequest.type = type;
        itemQuantityUpdateRequest.quantity = quantity;
        return itemQuantityUpdateRequest;
    }
}
