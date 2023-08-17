package com.example.crud_java.application.item;

import com.example.crud_java.application.common.DistributedLockService;
import com.example.crud_java.application.common.dto.PageDto;
import com.example.crud_java.application.item.dto.*;
import com.example.crud_java.application.item.exception.ItemNotFoundException;
import com.example.crud_java.application.item.exception.ItemQuantityException;
import com.example.crud_java.domain.item.Item;
import com.example.crud_java.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final DistributedLockService distributedLockService;

    @Transactional(readOnly = true)
    @Override
    public PageDto readItems(ItemReadRequest request) {
        Page<Item> items = itemRepository.findAllCustom(
                request.getPageable(),
                request.getKeyword(),
                request.getStartDate(),
                request.getEndDate()
        );

        return new PageDto(items.getContent(), items.getTotalElements());
    }

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

    @Transactional
    @Override
    public void updateItem(Long itemNo, ItemUpdateRequest request) {
        validateItemQuantity(request.getQuantity());

        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new ItemNotFoundException("상품을 찾을 수 없습니다."));

        item.update(request.getName(), request.getQuantity());
    }

    @Override
    public void updateItemQuantity(Long itemNo, ItemQuantityUpdateRequest request) {
        distributedLockService.doDistributedLock(() -> {
            Item item = itemRepository.findById(itemNo)
                    .orElseThrow(() -> new ItemNotFoundException("상품을 찾을 수 없습니다."));

            item.updateQuantity(request.getType(), request.getQuantity());
        }, "LOCK:updateItemQuantity:" + itemNo.toString());
    }

    @Transactional
    @Override
    public void deleteItem(Long itemNo) {
        Item item = itemRepository.findById(itemNo)
                .orElseThrow(() -> new ItemNotFoundException("상품을 찾을 수 없습니다."));

        item.delete();
    }

    private void validateItemQuantity(Long quantity) {
        if (quantity < 1) {
            throw new ItemQuantityException("상품 재고는 1개 이상이어야합니다.");
        }
    }
}
