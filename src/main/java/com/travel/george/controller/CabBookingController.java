package com.travel.george.controller;

import com.travel.george.model.BookingResponse;
import com.travel.george.model.FareResponse;
import com.travel.george.service.CabBookingService;
import com.travel.george.service.FareCalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoints:
 *   POST /cab/book  - from, to, typeOfCab       -> book the cab
 *   POST /cab/fare  - distance, typeOfCab        -> calculate the fare
 *   GET  /cab/fare  - same params, handy in a browser
 */
@RestController
@RequestMapping("/cab")
public class CabBookingController {

    private final CabBookingService bookingService;
    private final FareCalculatorService fareService;

    public CabBookingController(CabBookingService bookingService, FareCalculatorService fareService) {
        this.bookingService = bookingService;
        this.fareService = fareService;
    }

    @PostMapping("/book")
    public BookingResponse bookCab(@RequestParam("from") String from,
                                   @RequestParam("to") String to,
                                   @RequestParam("typeOfCab") int typeOfCab) {
        return bookingService.bookCab(from, to, typeOfCab);
    }

    @PostMapping("/fare")
    public FareResponse calculateFarePost(@RequestParam("distance") double distance,
                                          @RequestParam("typeOfCab") int typeOfCab) {
        return fareService.calculateFare(distance, typeOfCab);
    }

    @GetMapping("/fare")
    public FareResponse calculateFareGet(@RequestParam("distance") double distance,
                                         @RequestParam("typeOfCab") int typeOfCab) {
        return fareService.calculateFare(distance, typeOfCab);
    }
}
