package com.apps.quantitymeasurement;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // UC5 Conversion
    public QuantityLength convertTo(LengthUnit target) {

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = target.convertFromBaseUnit(baseValue);

        return new QuantityLength(convertedValue, target);
    }

    // UC7 Addition with target unit
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof QuantityLength))
            return false;

        QuantityLength other = (QuantityLength) obj;

        double baseValue1 = unit.convertToBaseUnit(value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(baseValue1 - baseValue2) < 0.0001;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}