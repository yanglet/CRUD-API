package com.example.crud_java.presentation.item;

import com.example.crud_java.application.common.dto.PageDto;
import com.example.crud_java.application.item.ItemService;
import com.example.crud_java.application.item.dto.ItemCreateRequest;
import com.example.crud_java.application.item.dto.ItemReadRequest;
import com.example.crud_java.application.item.dto.ItemReadResponse;
import com.example.crud_java.application.item.dto.ItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<PageDto> readItems(ItemReadRequest request) {
        PageDto response = itemService.readItems(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createItem(@RequestBody ItemCreateRequest request) {
        Long itemNo = itemService.createItem(request);
        URI uri = URI.create(String.format("/v1/items/%d", itemNo));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{itemNo}")
    public ResponseEntity<ItemReadResponse> readItem(@PathVariable Long itemNo) {
        ItemReadResponse response = itemService.readItem(itemNo);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{itemNo}")
    public ResponseEntity<Void> updateItem(
            @PathVariable Long itemNo,
            @RequestBody ItemUpdateRequest request
    ) {
        itemService.updateItem(itemNo, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{itemNo}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemNo) {
        itemService.deleteItem(itemNo);
        return ResponseEntity.noContent().build();
    }
}
