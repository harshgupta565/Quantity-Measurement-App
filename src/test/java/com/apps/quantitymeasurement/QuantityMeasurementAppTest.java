package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ---------------- UC1 Equality Tests ----------------

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q1.equals(null));
    }

    // ---------------- UC5 Conversion Tests ----------------

    @Test
    void testConvert_InchesToFeet() {
        QuantityLength q = new QuantityLength(24.0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET),
                q.convertTo(LengthUnit.FEET));
    }

    @Test
    void testConvert_YardsToFeet() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARDS);

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET),
                q.convertTo(LengthUnit.FEET));
    }

    // ---------------- UC6 Addition Tests ----------------

    @Test
    void testAddition_SameUnit() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET),
                q1.add(q2,LengthUnit.FEET));
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET),
                q1.add(q2,LengthUnit.FEET));
    }

    // ---------------- UC7 Addition With Target Unit ----------------

    @Test
    void testAddition_TargetUnit_Feet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET),
                q1.add(q2, LengthUnit.FEET));
    }

    @Test
    void testAddition_TargetUnit_Inches() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES),
                q1.add(q2, LengthUnit.INCHES));
    }

    // ---------------- UC8 Enum Conversion Tests ----------------

    @Test
    void testConvertToBaseUnit_Inches() {
        double base = LengthUnit.INCHES.convertToBaseUnit(12.0);

        assertEquals(1.0, base);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        double inches = LengthUnit.INCHES.convertFromBaseUnit(1.0);

        assertEquals(12.0, inches);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        double yards = LengthUnit.YARDS.convertFromBaseUnit(3.0);

        assertEquals(1.0, yards);
    }
}