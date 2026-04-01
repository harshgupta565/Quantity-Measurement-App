package com.apps.quantitymeasurement.unit;

import com.apps.quantitymeasurement.model.IMeasurable;

public enum WeightUnit implements IMeasurable {

    KG(1.0),
    GRAM(0.001),
    TONNE(1000);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}