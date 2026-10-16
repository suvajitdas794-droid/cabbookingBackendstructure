package com.travel.george.service;

import com.travel.george.model.CabType;
import com.travel.george.model.FareResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Microservice logic: calculate the fare = base fare + (distance x rate per km). */
@Service
public class FareCalculatorService {

    public FareResponse calculateFare(double distanceKm, int typeOfCab) {
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0 km.");
        }
        CabType cabType = CabType.fromCode(typeOfCab);

        double raw = cabType.getBaseFare() + distanceKm * cabType.getRatePerKm();
        double fare = BigDecimal.valueOf(raw).setScale(2, RoundingMode.HALF_UP).doubleValue();

        return new FareResponse(1, cabType.getDisplayName(), distanceKm, fare,
                "Fare for " + distanceKm + " km in a " + cabType.getDisplayName() + " cab is " + fare);
    }
}
