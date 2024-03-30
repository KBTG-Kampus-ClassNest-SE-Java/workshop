package com.kampus.kbazaar.model;

import lombok.Data;
import org.springframework.http.HttpHeaders;

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

    public void appendPageInHeader(HttpHeaders headers) {
        headers.add("x-total-pages", String.valueOf(totalPages));
        headers.add("x-total", String.valueOf(totalElements));
    }
}
