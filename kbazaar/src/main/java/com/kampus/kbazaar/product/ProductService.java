package com.kampus.kbazaar.product;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(
                        p ->
                                new ProductResponse(
                                        p.getId(),
                                        p.getName(),
                                        p.getSku(),
                                        p.getPrice(),
                                        p.getQuantity()))
                .toList();
    }

    public ProductResponse getBySku(String sku) {
        Optional<Product> product = productRepository.findBySku(sku);
        if (product.isEmpty()) {
            //            throw new ProductNotFoundException("Product not found");
            //            TODO: handle exception
            return null;
        }

        return product.get().toResponse();
    }
}
