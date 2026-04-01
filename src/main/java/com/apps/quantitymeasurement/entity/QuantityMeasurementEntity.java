package com.apps.quantitymeasurement.entity;
public class QuantityMeasurementEntity {

    private double value;
    private String unit;
    private String operation;
    private double result;

    public QuantityMeasurementEntity(double value, String unit,
                                     String operation, double result) {
        this.value = value;
        this.unit = unit;
        this.operation = operation;
        this.result = result;
    }

    public double getValue() { return value; }
    public String getUnit() { return unit; }
    public String getOperation() { return operation; }
    public double getResult() { return result; }
}
