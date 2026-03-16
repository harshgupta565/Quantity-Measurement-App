package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    // Constructor
    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------------- Conversion ----------------

    public Quantity<U> convertTo(U targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);
        double targetValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(targetValue), targetUnit);
    }

    // ---------------- Addition ----------------

    // UC6
    public Quantity<U> add(Quantity<U> other) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = unit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(result), unit);
    }

    // UC7
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(result), targetUnit);
    }

    // ---------------- Subtraction ----------------

    // UC8
    public Quantity<U> subtract(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Cannot subtract different measurement categories");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = this.unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), this.unit);
    }

    // Subtraction with target unit
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Cannot subtract different measurement categories");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }
    public Quantity<U> multiply(double factor) {

        if (!Double.isFinite(factor))
            throw new IllegalArgumentException("Invalid multiplication factor");

        double result = this.value * factor;

        return new Quantity<>(round(result), this.unit);
    }
    // ---------------- Division ----------------

    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Cannot divide different measurement categories");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
    }

    // ---------------- Equals ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> that = (Quantity<?>) obj;

        if (this.unit.getClass() != that.unit.getClass())
            return false;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = that.unit.convertToBaseUnit(that.value);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {

        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(baseValue, unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    // ---------------- Helper ----------------

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}