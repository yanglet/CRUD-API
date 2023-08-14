package com.example.crud_java.application.item;

import com.example.crud_java.application.item.dto.ItemCreateRequest;
import com.example.crud_java.application.item.dto.ItemReadResponse;
import com.example.crud_java.application.item.exception.ItemNotFoundException;
import com.example.crud_java.application.item.exception.ItemQuantityException;
import com.example.crud_java.domain.item.Item;
import com.example.crud_java.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public Long createItem(ItemCreateRequest request) {
        validateItemQuantity(request.getQuantity());

        Item item = Item.of(request.getName(), request.getQuantity());
        itemRepository.save(item);

        return item.getItemNo();
    }

    @Transactional(readOnly = true)
    @Override
    public ItemReadResponse readItem(Long itemNo) {
        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new ItemNotFoundException("상품을 찾을 수 없습니다."));

        return ItemReadResponse.of(
                item.getItemNo(),
                item.getName(),
                item.getQuantity(),
                item.getInsertDate(),
                item.getInsertOperator(),
                item.getUpdateDate(),
                item.getUpdateOperator()
        );
    }

    private void validateItemQuantity(Long quantity) {
        if (quantity < 1) {
            throw new ItemQuantityException("상품 재고는 1개 이상이어야합니다.");
        }
    }
}
