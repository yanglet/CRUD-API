package com.example.crud_java.application.item.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemReadResponse {
    private Long itemNo;
    private String name;
    private Long quantity;
    private LocalDateTime insertDate;
    private String insertOperator;
    private LocalDateTime updateDate;
    private String updateOperator;

    public static ItemReadResponse of(Long itemNo, String name, Long quantity, LocalDateTime insertDate, String insertOperator, LocalDateTime updateDate, String updateOperator) {
        ItemReadResponse itemReadResponse = new ItemReadResponse();
        itemReadResponse.itemNo = itemNo;
        itemReadResponse.name = name;
        itemReadResponse.quantity = quantity;
        itemReadResponse.insertDate = insertDate;
        itemReadResponse.insertOperator = insertOperator;
        itemReadResponse.updateDate = updateDate;
        itemReadResponse.updateOperator = updateOperator;
        return itemReadResponse;
    }
}
