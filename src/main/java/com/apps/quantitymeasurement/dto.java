package com.apps.quantitymeasurement;

public class dto {
    public static interface IMeasurable {

        double getConversionFactor();

        default double convertToBaseUnit(double value) {
            return value * getConversionFactor();
        }

        default double convertFromBaseUnit(double baseValue) {
            return baseValue / getConversionFactor();
        }

        String getUnitName();

        //  NEW PART STARTS HERE

        SupportsArithmetic supportsArithmetic = () -> true;

        default boolean supportsArithmetic() {
            return supportsArithmetic.isSupported();
        }

        default void validateOperationSupport(String operation) {
            // default: sab allowed
        }
    }
}
