package com.kampus.kbazaar.promotion;

import com.kampus.kbazaar.exceptions.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    private PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<PromotionResponse> getAll() {
        return promotionRepository.findAll().stream().map(p -> p.toResponse()).toList();
    }

    public PromotionResponse getPromotionByCode(String code) {
        return promotionRepository
                .findByCode(code)
                .map(p -> p.toResponse())
                .orElseThrow(() -> new NotFoundException("Promotion not found"));
    }
}
