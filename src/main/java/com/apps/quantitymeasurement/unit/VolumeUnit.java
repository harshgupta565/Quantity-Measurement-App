package com.apps.quantitymeasurement.unit;

import com.apps.quantitymeasurement.model.IMeasurable;

public enum VolumeUnit implements IMeasurable {

    LITER(1.0),
    MILLILITER(0.001),
    GALLON(3.78);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}