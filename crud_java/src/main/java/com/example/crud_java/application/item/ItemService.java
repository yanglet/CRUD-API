package com.example.crud_java.application.item;

import com.example.crud_java.application.common.dto.PageDto;
import com.example.crud_java.application.item.dto.*;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    PageDto readItems(Pageable pageable, ItemReadRequest request);
    Long createItem(ItemCreateRequest request);
    ItemReadResponse readItem(Long itemNo);
    void updateItem(Long itemNo, ItemUpdateRequest request);
    void updateItemQuantity(Long itemNo, ItemQuantityUpdateRequest request);
    void deleteItem(Long itemNo);
}
