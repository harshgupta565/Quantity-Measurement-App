package com.apps.quantitymeasurement.model;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------------- Conversion ----------------

    public Quantity<U> convertTo(U targetUnit) {

        if (this.unit.getClass() != targetUnit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(round(converted), targetUnit);
    }

    // ---------------- Arithmetic Operations ----------------

    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, unit, false, ArithmeticOperation.ADD);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true, ArithmeticOperation.ADD);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, unit, false, ArithmeticOperation.SUBTRACT);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true, ArithmeticOperation.SUBTRACT);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false, ArithmeticOperation.DIVIDE);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    public Quantity<U> multiply(double factor) {
        if (!Double.isFinite(factor)) {
            throw new IllegalArgumentException("Invalid multiplication factor");
        }
        return new Quantity<>(round(this.value * factor), this.unit);
    }

    // ---------------- Centralized Helper ----------------

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    // ---------------- Validation Helper ----------------

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired,
            ArithmeticOperation operation
    ) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Cannot operate on different measurement categories");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");


        this.unit.validateOperationSupport(operation.name());
        other.unit.validateOperationSupport(operation.name());
    }

    // ---------------- Arithmetic Operation Enum ----------------

    private enum ArithmeticOperation {

        ADD {
            public double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            public double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            public double compute(double a, double b) {

                if (b == 0)
                    throw new ArithmeticException("Division by zero");

                return a / b;
            }
        };

        public abstract double compute(double a, double b);
    }

    // ---------------- Equality ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> that = (Quantity<?>) obj;

        if (this.unit.getClass() != that.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = that.unit.convertToBaseUnit(that.value);

        return Math.abs(base1 - base2) < 0.0001;
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

    // ---------------- Utility ----------------

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}