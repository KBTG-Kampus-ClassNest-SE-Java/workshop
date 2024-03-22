package com.kampus.kbazaar.promotion;

import com.kampus.kbazaar.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PromotionController {
    private PromotionService promotionService;

    @Value("${feature.toggle.promotion-api:true}")
    private boolean promotionApi;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @ApiResponse(
            responseCode = "200",
            description = "list all promotions",
            content = {
                @Content(
                        mediaType = "application/json",
                        array =
                                @ArraySchema(
                                        schema = @Schema(implementation = PromotionResponse.class)))
            })
    @ApiResponse(
            responseCode = "500",
            description = "internal server error",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class)))
    @GetMapping("/promotions")
    public ResponseEntity<List<PromotionResponse>> getAllPromotions() {
        if (!promotionApi) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(promotionService.getAll());
    }

    @ApiResponse(
            responseCode = "200",
            description = "get promotion by code",
            content = {
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PromotionResponse.class))
            })
    @ApiResponse(
            responseCode = "404",
            description = "promotion not found",
            content = {
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = NotFoundException.class))
            })
    @GetMapping("/promotions/{code}")
    public PromotionResponse getPromotionByCode(@PathVariable String code) {
        return promotionService.getPromotionByCode(code);
    }
}
