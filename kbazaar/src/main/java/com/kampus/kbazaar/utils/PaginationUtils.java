package com.kampus.kbazaar.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class PaginationUtils {

    public static void appendPageInHeader(HttpHeaders headers, Page page) {
        headers.add("x-total-pages", String.valueOf(page.getTotalPages()));
        headers.add("x-total", String.valueOf(page.getTotalElements()));
    }
}
