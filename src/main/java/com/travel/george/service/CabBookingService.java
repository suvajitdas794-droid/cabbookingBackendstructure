package com.travel.george.service;

import com.travel.george.model.BookingResponse;
import com.travel.george.model.CabType;
import org.springframework.stereotype.Service;

/** Microservice logic: book a cab. */
@Service
public class CabBookingService {

    public static final int SUCCESS = 1;

    public BookingResponse bookCab(String from, String to, int typeOfCab) {
        if (from == null || from.isBlank()) {
            throw new IllegalArgumentException("From location is required.");
        }
        if (to == null || to.isBlank()) {
            throw new IllegalArgumentException("To location is required.");
        }
        CabType cabType = CabType.fromCode(typeOfCab);

        String message = cabType.getDisplayName() + " Cab Booked from "
                + from.trim() + " to " + to.trim();
        return new BookingResponse(SUCCESS, message);
    }
}
