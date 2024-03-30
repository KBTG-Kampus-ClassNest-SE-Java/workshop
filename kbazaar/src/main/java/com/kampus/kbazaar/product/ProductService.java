package com.kampus.kbazaar.product;

import com.kampus.kbazaar.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;

import com.kampus.kbazaar.model.PaginationResponse;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PaginationResponse<ProductResponse> getAll(int page , int limit) {
        val pageReq = PageRequest.of(page - 1, limit);
        val pageRes = productRepository.findAll(pageReq);

        val totalPage = pageRes.getTotalPages();
        val totalElements = pageRes.getTotalElements();

        val data =  pageRes.stream().map(Product::toResponse).toList();

        return new PaginationResponse<>(data, totalPage, totalElements);
    }

    public ProductResponse getBySku(String sku) {
        Optional<Product> product = productRepository.findBySku(sku);
        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return product.get().toResponse();
    }
}
