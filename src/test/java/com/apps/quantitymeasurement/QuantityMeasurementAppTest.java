package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ---------------- UC1 Equality Tests ----------------

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        Quantity<LengthUnit> q1 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_NullComparison() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(q1.equals(null));
    }

    // ---------------- UC5 Conversion Tests ----------------

    @Test
    void testConvert_InchesToFeet() {
        Quantity<LengthUnit> q = new Quantity<>(24.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET),
                q.convertTo(LengthUnit.FEET));
    }

    @Test
    void testConvert_YardsToFeet() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.YARDS);

        assertEquals(new Quantity<>(3.0, LengthUnit.FEET),
                q.convertTo(LengthUnit.FEET));
    }

    // ---------------- UC6 Addition Tests ----------------

    @Test
    void testAddition_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(new Quantity<>(3.0, LengthUnit.FEET),
                q1.add(q2, LengthUnit.FEET));
    }

    @Test
    void testAddition_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET),
                q1.add(q2, LengthUnit.FEET));
    }

    // ---------------- UC7 Addition With Target Unit ----------------

    @Test
    void testAddition_TargetUnit_Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(24.0, LengthUnit.INCHES),
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

    // ---------------- Weight Tests (UC9 preserved in UC10) ----------------

    @Test
    void testEquality_KilogramToKilogram_SameValue() {

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(w1, w2);
    }

    @Test
    void testEquality_KilogramToGram() {

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(w1, w2);
    }

    @Test
    void testConversion_KilogramToPound() {

        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = w.convertTo(WeightUnit.POUND);

        assertEquals(new Quantity<>(2.20462, WeightUnit.POUND), result);
    }

    @Test
    void testWeightVsLength_Incompatible() {

        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }
    @Test
    void testEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }
    @Test
    void testAddition_LitrePlusMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(2.0, result.getValue(), 0.01);
    }
    @Test
    void testVolumeVsLength() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }
}