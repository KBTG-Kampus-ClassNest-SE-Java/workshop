package com.kampus.kbazaar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test-application.properties")
class KBazaarApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:12-alpine");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
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
    void getUsers_shouldReturnUsers() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                        .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertEquals("[{\"id\":1,\"name\":\"John Doe\",\"email\":\"john@mailsy.com\"},{\"id\":2,\"name\":\"Jane Doe\",\"email\":\"jane@mailsy.com\"}]", contentAsString);
    }
}
