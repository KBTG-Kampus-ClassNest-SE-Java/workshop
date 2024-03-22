package com.kampus.kbazaar.shopper;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kampus.kbazaar.security.JwtAuthFilter;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(
        controllers = ShopperController.class,
        excludeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = JwtAuthFilter.class))
public class ShopperControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private ShopperService shopperService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return all users")
    public void getAllUsers_ShouldReturnAllUsers() throws Exception {
        // Given

        // When & Then
        when(shopperService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/shoppers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(shopperService, times(1)).getAll();
    }

    @Test
    @DisplayName("should return shopper response")
    public void shouldReturnShopperResponse() throws Exception {
        // Given
        String username = "cat";

        // When & Then
        when(shopperService.getByUsername(username)).thenReturn(new ShopperResponse(1L, "cat", ""));

        mockMvc.perform(get("/api/v1/shoppers/" + username).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));

        verify(shopperService, times(1)).getByUsername(username);
    }
}
