package com.example.crud_java.application.item.dto;

import com.example.crud_java.domain.item.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
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

    public static ItemReadResponse from(Item item) {
        ItemReadResponse itemReadResponse = new ItemReadResponse();
        itemReadResponse.itemNo = item.getItemNo();
        itemReadResponse.name = item.getName();
        itemReadResponse.quantity = item.getQuantity();
        itemReadResponse.insertDate = item.getInsertDate();
        itemReadResponse.insertOperator = item.getInsertOperator();
        itemReadResponse.updateDate = item.getUpdateDate();
        itemReadResponse.updateOperator = item.getUpdateOperator();
        return itemReadResponse;
    }
}
