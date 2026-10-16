package com.travel.george.model;

/** Response returned by the booking microservice. code 1 = success, 0 = error. */
public record BookingResponse(int code, String message) {
}
