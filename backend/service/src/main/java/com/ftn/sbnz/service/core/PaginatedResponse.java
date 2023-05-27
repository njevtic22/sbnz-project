package com.ftn.sbnz.service.core;

import java.util.List;

public class PaginatedResponse<T> {
    private final List<T> data;
    private final long totalElements;
    private final int totalPages;

    public PaginatedResponse(List<T> data, long totalElements, int totalPages) {
        this.data = data;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

