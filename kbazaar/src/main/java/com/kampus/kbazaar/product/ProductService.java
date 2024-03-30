package com.kampus.kbazaar.product;

import com.kampus.kbazaar.exceptions.NotFoundException;

import java.util.Optional;

import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductResponse> getAll(int page , int limit) {
        val pageReq = PageRequest.of(page - 1, limit);

        return productRepository.findAll(pageReq).map(Product::toResponse);
    }

    public ProductResponse getBySku(String sku) {
        Optional<Product> product = productRepository.findBySku(sku);
        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return product.get().toResponse();
    }
}
