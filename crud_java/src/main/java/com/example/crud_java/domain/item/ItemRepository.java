package com.example.crud_java.domain.item;

import java.util.Optional;

public interface ItemRepository {
    void save(Item item);
    Optional<Item> findById(Long itemNo);
}