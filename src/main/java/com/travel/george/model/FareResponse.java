package com.travel.george.model;

/** Response returned by the fare calculation microservice. */
public record FareResponse(int code, String cabType, double distanceKm, double fare, String message) {
}
