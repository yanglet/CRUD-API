package com.example.crud_java.application.item.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Controller 에서 RequestBody 로 받아올 때 리플렉션을 이용하므로
 * 생성자가 전부 PRIVATE 여도 괜찮음.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemCreateRequest {

    private String name;
    private Long quantity;

    public static ItemCreateRequest of(String name, Long quantity) {
        ItemCreateRequest itemCreateRequest = new ItemCreateRequest();
        itemCreateRequest.name = name;
        itemCreateRequest.quantity = quantity;
        return itemCreateRequest;
    }
}