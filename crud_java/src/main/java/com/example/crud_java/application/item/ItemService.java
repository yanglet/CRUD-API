package com.example.crud_java.application.item;

import com.example.crud_java.application.item.dto.ItemCreateRequest;
import com.example.crud_java.application.item.dto.ItemReadResponse;

public interface ItemService {
    Long createItem(ItemCreateRequest request);
    ItemReadResponse readItem(Long itemNo);
}
