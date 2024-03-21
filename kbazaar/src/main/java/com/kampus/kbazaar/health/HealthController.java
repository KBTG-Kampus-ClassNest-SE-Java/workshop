package com.kampus.kbazaar.health;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @ApiResponse(
            description = "Health check",
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    @GetMapping("/health")
    public String health() {
        return "{ \"status\": \"alive\" }";
    }
}
