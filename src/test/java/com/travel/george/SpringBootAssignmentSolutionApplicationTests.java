package com.travel.george;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.travel.george.controller.CabBookingController;

@SpringBootTest
class SpringBootAssignmentSolutionApplicationTests {

    @Autowired
    private CabBookingController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
