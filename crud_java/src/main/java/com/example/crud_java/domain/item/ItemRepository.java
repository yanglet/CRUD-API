package com.example.crud_java.domain.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ItemRepository {
    Page<Item> findAllCustom(Pageable pageable, String keyword, LocalDateTime startDate, LocalDateTime endDate);
    void save(Item item);
    Optional<Item> findById(Long itemNo);
}