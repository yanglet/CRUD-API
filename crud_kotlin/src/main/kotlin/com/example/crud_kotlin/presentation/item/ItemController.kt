package com.example.crud_kotlin.presentation.item

import com.example.crud_kotlin.application.common.dto.PageDto
import com.example.crud_kotlin.application.item.ItemService
import com.example.crud_kotlin.application.item.dto.*
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/v1/items")
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping
    fun readItems(
        @PageableDefault(size = 20) pageable: Pageable,
        request: ItemReadRequest
    ): ResponseEntity<PageDto<Iterable<ItemReadResponse>>> =
        ResponseEntity.ok(
            itemService.readItems(pageable, request)
        )

    @PostMapping
    fun createItem(@RequestBody request: ItemCreateRequest): ResponseEntity<Unit> {
        val itemNo = itemService.createItem(request)
        val uri = URI("/v1/items/$itemNo")
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/{itemNo}")
    fun readItem(@PathVariable itemNo: Long): ResponseEntity<ItemReadResponse> =
        ResponseEntity.ok(
            itemService.readItem(itemNo)
        )

    @PutMapping("/{itemNo}")
    fun updateItem(
        @PathVariable itemNo: Long,
        @RequestBody request: ItemUpdateRequest
    ): ResponseEntity<Unit> {
        itemService.updateItem(itemNo, request)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{itemNo}")
    fun updateItemQuantity(
        @PathVariable itemNo: Long,
        @RequestBody request: ItemUpdateQuantityRequest
    ): ResponseEntity<Unit> {
        itemService.updateItemQuantity(itemNo, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{itemNo}")
    fun deleteItem(@PathVariable itemNo: Long): ResponseEntity<Unit> {
        itemService.deleteItem(itemNo)
        return ResponseEntity.noContent().build()
    }
}