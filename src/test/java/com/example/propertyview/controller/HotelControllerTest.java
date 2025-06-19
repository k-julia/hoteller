package com.example.propertyview.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllHotelsReturnsOk() throws Exception {
        mockMvc.perform(get("/property-view/hotels"))
                .andExpect(status().isOk());
    }

    @Test
    void getHistogramForValidReturnsOk() throws Exception {
        mockMvc.perform(get("/property-view/histogram/brand"))
                .andExpect(status().isOk());
    }

    @Test
    void createHotelWithInvalidDataReturnsBadRequest() throws Exception {
        String invalidJson = "{\"name\":\"TestName\"}";
        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getHistogramForInvalidParamReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/property-view/histogram/unknown"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getHotelNotFoundReturnsNotFound() throws Exception {
        mockMvc.perform(get("/property-view/hotels/9999"))
                .andExpect(status().isNotFound());
    }
} 