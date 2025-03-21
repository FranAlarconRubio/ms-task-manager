package com.mothdev.taskmanager.mapper;

import com.mothdev.taskmanager.payload.PagedResponse;
import org.springframework.data.domain.Page;

public abstract class PaginationMapper {
    public static <T> PagedResponse<T> map(Page<T> page) {
        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
