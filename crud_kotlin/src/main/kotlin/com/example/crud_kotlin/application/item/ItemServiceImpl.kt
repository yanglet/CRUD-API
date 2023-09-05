package com.example.crud_kotlin.application.item

import com.example.crud_kotlin.application.item.dto.ItemCreateRequest
import com.example.crud_kotlin.application.item.dto.ItemReadResponse
import com.example.crud_kotlin.application.item.exception.ItemNotFoundException
import com.example.crud_kotlin.application.item.exception.ItemQuantityException
import com.example.crud_kotlin.domain.item.Item
import com.example.crud_kotlin.domain.item.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemServiceImpl(
    private val itemRepository: ItemRepository
) : ItemService {
    @Transactional
    override fun createItem(request: ItemCreateRequest): Long {
        validateItemQuantity(request.quantity)

        val item = Item(name = request.name, quantity = request.quantity)
        itemRepository.save(item)

        return item.itemNo
    }

    @Transactional(readOnly = true)
    override fun readItem(itemNo: Long): ItemReadResponse {
        val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException("상품을 찾을 수 없습니다.")
        return item.toReadResponse()
    }

    private fun validateItemQuantity(quantity: Long) {
        if (quantity < 1) {
            throw ItemQuantityException("상품 재고는 1개 이상이어야합니다.")
        }
    }

    private fun Item.toReadResponse() = ItemReadResponse(
        itemNo = this.itemNo,
        name = this.name,
        quantity = this.quantity,
        deleted = this.deleted,
        deletedDate = this.deletedDate
    )
}