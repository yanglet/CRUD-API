package com.example.crud_java.infra.item;

import com.example.crud_java.domain.item.Item;
import com.example.crud_java.domain.item.ItemRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public void save(Item item) {
        itemJpaRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long itemNo) {
        return itemJpaRepository.findById(itemNo);
    }
}
