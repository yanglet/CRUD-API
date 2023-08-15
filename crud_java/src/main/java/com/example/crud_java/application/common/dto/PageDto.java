package com.example.crud_java.application.common.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageDto<T> {
    private T content;
    private Long count;

    public PageDto(T content, Long count) {
        this.content = content;
        this.count = count;
    }
}
