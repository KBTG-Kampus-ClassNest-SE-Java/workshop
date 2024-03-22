package com.kampus.kbazaar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Tag("integration-test")
@TestPropertySource(locations = "classpath:application-ittest.properties")
class KBazaarApplicationIT {

    @Autowired private MockMvc mockMvc;

    private final String jwtToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1NIT1BQRVIiXSwic3ViIjoic2hvcHBlciIsImlhdCI6MTcxMTA4MTk1OSwiZXhwIjoxNzQyNjE3OTU5fQ.hwpc_6CL_ZENHurOaZEYg3tz9FVBgOYa7ILF063stxs";

    static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:12-alpine");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("security.jwt.secret", () -> "1ukPr@a1M@1T@1D3rN@NgJoNMaHenKubT@");
    }

    @BeforeAll
    static void startContainer() {
        postgresContainer.start();
    }

    @AfterAll
    static void stopContainer() {
        postgresContainer.stop();
    }

    @Test
    @DisplayName("should return shopper list")
    void getShopper_shouldReturnShopperList() throws Exception {
        mockMvc.perform(get("/api/v1/shoppers").header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(Matchers.greaterThan(0)))
                .andExpect(jsonPath("$[0].username").value("TechNinja"))
                .andReturn();
    }

    @Test
    @DisplayName("should return shopper by username")
    void getShopperByName_shouldReturnShopper() throws Exception {
        String username = "TechNinja";

        mockMvc.perform(
                        get("/api/v1/shoppers/" + username)
                                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username))
                .andReturn();
    }
}
