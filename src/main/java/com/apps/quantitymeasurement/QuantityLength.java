package com.apps.quantitymeasurement;

enum LengthUnit {

    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // UC5 Conversion
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        return value * (source.getConversionFactor() / target.getConversionFactor());
    }

    // Instance conversion
    public QuantityLength convertTo(LengthUnit target) {
        return new QuantityLength(convert(value, unit, target), target);
    }

    //  UC6 Addition Method
    public QuantityLength add(QuantityLength other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        // convert both to base unit (inches)
        double base1 = this.value * this.unit.getConversionFactor();
        double base2 = other.value * other.unit.getConversionFactor();

        double sumBase = base1 + base2;

        // convert result back to first operand unit
        double resultValue = sumBase / this.unit.getConversionFactor();

        return new QuantityLength(resultValue, this.unit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(
                this.value * this.unit.getConversionFactor(),
                other.value * other.unit.getConversionFactor()
        ) == 0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}