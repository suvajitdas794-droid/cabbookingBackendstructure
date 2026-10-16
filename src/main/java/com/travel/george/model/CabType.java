package com.travel.george.model;

/**
 * Cab categories offered by George's travel company.
 * The numeric code is what the front end / Postman sends as "typeOfCab".
 */
public enum CabType {

    ECONOMY(1, "Economy", 50.0, 12.0),
    LUXURY(2, "Luxury", 100.0, 25.0),
    SUV(3, "SUV", 70.0, 18.0);

    private final int code;
    private final String displayName;
    private final double baseFare;
    private final double ratePerKm;

    CabType(int code, String displayName, double baseFare, double ratePerKm) {
        this.code = code;
        this.displayName = displayName;
        this.baseFare = baseFare;
        this.ratePerKm = ratePerKm;
    }

    public int getCode() { return code; }
    public String getDisplayName() { return displayName; }
    public double getBaseFare() { return baseFare; }
    public double getRatePerKm() { return ratePerKm; }

    public static CabType fromCode(int code) {
        for (CabType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                "Invalid cab type: " + code + ". Use 1 (Economy), 2 (Luxury) or 3 (SUV).");
    }
}
