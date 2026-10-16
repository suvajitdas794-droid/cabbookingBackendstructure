package com.travel.george.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.travel.george.model.BookingResponse;
import com.travel.george.model.FareResponse;

class CabServicesTest {

    private final CabBookingService bookingService = new CabBookingService();
    private final FareCalculatorService fareService = new FareCalculatorService();

    @Test
    void booking_buildsExpectedMessage() {
        BookingResponse r = bookingService.bookCab(" Home ", " Work ", 2);
        assertThat(r.code()).isEqualTo(1);
        assertThat(r.message()).isEqualTo("Luxury Cab Booked from Home to Work");
    }

    @Test
    void booking_rejectsBlankLocation() {
        assertThatThrownBy(() -> bookingService.bookCab("", "Work", 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void booking_rejectsUnknownCabType() {
        assertThatThrownBy(() -> bookingService.bookCab("A", "B", 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid cab type");
    }

    @Test
    void fare_suv() {
        // 70 + 20 * 18 = 430
        FareResponse r = fareService.calculateFare(20, 3);
        assertThat(r.fare()).isEqualTo(430.0);
        assertThat(r.cabType()).isEqualTo("SUV");
    }

    @Test
    void fare_roundsToTwoDecimals() {
        // 50 + 3.333 * 12 = 89.996 -> 90.00
        assertThat(fareService.calculateFare(3.333, 1).fare()).isEqualTo(90.0);
    }

    @Test
    void fare_rejectsNegativeDistance() {
        assertThatThrownBy(() -> fareService.calculateFare(-5, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
