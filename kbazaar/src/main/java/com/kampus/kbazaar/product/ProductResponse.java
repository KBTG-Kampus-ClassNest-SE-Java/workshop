package com.kampus.kbazaar.product;

public record ProductResponse(Long id, String name, String sku, double price, int quantity) {}