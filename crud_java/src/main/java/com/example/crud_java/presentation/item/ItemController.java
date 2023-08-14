package com.example.crud_java.presentation.item;

import com.example.crud_java.application.item.ItemService;
import com.example.crud_java.application.item.dto.ItemCreateRequest;
import com.example.crud_java.application.item.dto.ItemReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {
    private final ItemService itemService;

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
}
