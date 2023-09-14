package com.example.crud_kotlin

import com.example.crud_kotlin.application.item.ItemService
import com.example.crud_kotlin.application.item.dto.ItemCreateRequest
import com.example.crud_kotlin.application.item.dto.ItemUpdateQuantityRequest
import com.example.crud_kotlin.application.item.exception.ItemNotFoundException
import com.example.crud_kotlin.domain.item.ItemRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@TestPropertySource(properties = ["spring.profiles.active = test"])
class ConcurrencyBehaviorSpec(
    private val itemService: ItemService,
    private val itemRepository: ItemRepository
) : BehaviorSpec({

    afterTest {
        itemRepository.deleteAll()
    }

    Given("상품 재고가 100개 있을 때") {
        val itemNo = itemService.createItem(
            ItemCreateRequest(name = "First_Item", quantity = 100L)
        )

        When("50명이 동시에 재고 차감을 시도할 경우") {
            val threadPool = Executors.newFixedThreadPool(50)
            val latch = CountDownLatch(50)

            for (i: Int in 1..50) {
                threadPool.submit {
                    try {
                        itemService.updateItemQuantity(
                            itemNo,
                            ItemUpdateQuantityRequest(type = "MINUS", quantity = 1L)
                        )
                    } finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()

            Then("상품 재고는 50개가 된다.") {
                val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException("상품을 찾을 수 없습니다.")

                item.itemNo shouldBe itemNo
                item.quantity shouldBe 50L
            }
        }
    }
})