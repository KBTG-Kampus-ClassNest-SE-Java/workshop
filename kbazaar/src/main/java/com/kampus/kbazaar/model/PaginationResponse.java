package com.kampus.kbazaar.model;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponse<T> {
    private List<T> data;

    private int totalPages;

    private long totalElements;

    public PaginationResponse(List<T> data, int totalPages, long totalElements) {
        this.data = data;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
