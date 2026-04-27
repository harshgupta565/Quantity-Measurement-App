package com.apps.quantitymeasurement.model;
public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        @Override
        public double convertToBaseUnit(double value) {
            return value; // base unit = Celsius
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue;
        }
    },

    FAHRENHEIT {
        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5 / 9;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return (baseValue * 9 / 5) + 32;
        }
    },

    KELVIN {
        @Override
        public double convertToBaseUnit(double value) {
            return value - 273.15;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue + 273.15;
        }
    };

    // Not used for temperature (non-linear conversion)
    @Override
    public double getConversionFactor() {
        return 1;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }
    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation +
                        " (only conversion and equality are allowed)"
        );
    }
}