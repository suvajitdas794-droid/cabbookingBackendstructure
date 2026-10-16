package com.travel.george.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CabBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void bookLuxuryCab_returnsSuccessMessage() throws Exception {
        mockMvc.perform(post("/cab/book")
                        .param("from", "Home").param("to", "Work").param("typeOfCab", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.message").value("Luxury Cab Booked from Home to Work"));
    }

    @Test
    void bookEconomyCab_returnsEconomyMessage() throws Exception {
        mockMvc.perform(post("/cab/book")
                        .param("from", "Airport").param("to", "Hotel").param("typeOfCab", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Economy Cab Booked from Airport to Hotel"));
    }

    @Test
    void bookCab_invalidCabType_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/cab/book")
                        .param("from", "Home").param("to", "Work").param("typeOfCab", "9"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void bookCab_missingParameter_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/cab/book").param("from", "Home").param("typeOfCab", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void bookCab_nonNumericCabType_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/cab/book")
                        .param("from", "Home").param("to", "Work").param("typeOfCab", "abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void calculateFare_luxury10km() throws Exception {
        // 100 + 10 * 25 = 350
        mockMvc.perform(post("/cab/fare").param("distance", "10").param("typeOfCab", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.cabType").value("Luxury"))
                .andExpect(jsonPath("$.fare").value(350.0));
    }

    @Test
    void calculateFare_viaGet() throws Exception {
        // 50 + 5 * 12 = 110
        mockMvc.perform(get("/cab/fare").param("distance", "5").param("typeOfCab", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fare").value(110.0));
    }

    @Test
    void calculateFare_zeroDistance_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/cab/fare").param("distance", "0").param("typeOfCab", "2"))
                .andExpect(status().isBadRequest());
    }
}
