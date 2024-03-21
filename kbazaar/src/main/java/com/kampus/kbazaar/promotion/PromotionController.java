package com.kampus.kbazaar.promotion;

import com.kampus.kbazaar.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PromotionController {
    private PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "list all promotions",
                content = {
                    @Content(
                            mediaType = "application/json",
                            array =
                                    @ArraySchema(
                                            schema =
                                                    @Schema(
                                                            implementation =
                                                                    PromotionResponse.class)))
                }),
        @ApiResponse(
                responseCode = "500",
                description = "internal server error",
                content =
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = NotFoundException.class)))
    })
    @GetMapping("/promotions")
    public List<PromotionResponse> getAllPromotions() {
        return promotionService.getAll();
    }

    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "get promotion by code",
                content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PromotionResponse.class))
                }),
        @ApiResponse(
                responseCode = "404",
                description = "promotion not found",
                content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotFoundException.class))
                })
    })
    @GetMapping("/promotions/{code}")
    public PromotionResponse getPromotionByCode(String code) {
        return promotionService.getPromotionByCode(code);
    }
}
