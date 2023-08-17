package com.example.crud_java;

import com.example.crud_java.application.item.ItemService;
import com.example.crud_java.application.item.dto.ItemCreateRequest;
import com.example.crud_java.application.item.dto.ItemQuantityUpdateRequest;
import com.example.crud_java.application.item.exception.ItemNotFoundException;
import com.example.crud_java.domain.item.Item;
import com.example.crud_java.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class ConcurrencyTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void 상품재고차감_동시성_테스트() throws InterruptedException {
        int numberOfThreads = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        Long itemNo = itemService.createItem(ItemCreateRequest.of("TEST_ITEM", 150L));

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    itemService.updateItemQuantity(
                            itemNo, ItemQuantityUpdateRequest.of("MINUS", 1L)
                    );
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Item result = itemRepository.findById(itemNo)
                .orElseThrow(() -> new ItemNotFoundException("상품을 찾을 수 없습니다."));

        Assertions.assertThat(result.getQuantity()).isEqualTo(130L);
    }
}
