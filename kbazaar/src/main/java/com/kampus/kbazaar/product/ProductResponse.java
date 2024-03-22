package com.kampus.kbazaar.product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, String sku, BigDecimal price, int quantity) {}
