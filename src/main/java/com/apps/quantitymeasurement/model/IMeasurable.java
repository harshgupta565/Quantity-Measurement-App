package com.apps.quantitymeasurement.model;

public interface IMeasurable {

    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();

    //  NEW PART STARTS HERE

    default boolean supportsArithmetic() {
        return true;
    }

    default void validateOperationSupport(String operation) {
        // default: sab allowed
    }
}