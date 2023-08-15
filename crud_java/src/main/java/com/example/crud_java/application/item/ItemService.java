package com.example.crud_java.application.item;

import com.example.crud_java.application.common.dto.PageDto;
import com.example.crud_java.application.item.dto.ItemCreateRequest;
import com.example.crud_java.application.item.dto.ItemReadRequest;
import com.example.crud_java.application.item.dto.ItemReadResponse;
import com.example.crud_java.application.item.dto.ItemUpdateRequest;

public interface ItemService {
    PageDto readItems(ItemReadRequest request);
    Long createItem(ItemCreateRequest request);
    ItemReadResponse readItem(Long itemNo);
    void updateItem(Long itemNo, ItemUpdateRequest request);
    void deleteItem(Long itemNo);
}
