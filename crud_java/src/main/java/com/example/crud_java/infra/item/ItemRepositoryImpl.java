package com.example.crud_java.infra.item;

import com.example.crud_java.domain.item.Item;
import com.example.crud_java.domain.item.ItemRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.crud_java.domain.item.QItem.item;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Item> findAllCustom(Pageable pageable, String keyword, LocalDateTime startDate, LocalDateTime endDate) {
        List<Item> content = queryFactory.selectFrom(item)
                .where(
                        containsKeyword(keyword),
                        betweenDate(startDate, endDate)
                )
                .orderBy(item.insertDate.desc())
                .fetch();

        List<Long> itemNos = queryFactory.select(item.itemNo)
                .from(item)
                .where(
                        containsKeyword(keyword),
                        betweenDate(startDate, endDate)
                )
                .fetch();

        return new PageImpl<>(content, pageable, itemNos.size());
    }

    @Override
    public void save(Item item) {
        itemJpaRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long itemNo) {
        return itemJpaRepository.findById(itemNo);
    }

    private BooleanExpression containsKeyword(String keyword) {
        if (keyword == null) {
            return null;
        }

        return item.name.contains(keyword);
    }

    private BooleanExpression betweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }

        /** 인덱스 태우고 싶으면 이렇게 하면 안됨 */
        return item.insertDate.between(startDate, endDate);
    }
}
