package com.apps.quantitymeasurement;


public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert value in this unit to base unit (FEET)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert value from base unit (FEET) to this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}
