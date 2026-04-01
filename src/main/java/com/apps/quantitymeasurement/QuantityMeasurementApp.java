package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityController;
import com.apps.quantitymeasurement.model.*;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityController controller = new QuantityController();

        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        controller.demonstrateEquality(l1, l2);
        controller.demonstrateConversion(l1, LengthUnit.INCHES);
        controller.demonstrateAddition(l1, l2, LengthUnit.FEET);

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        controller.demonstrateEquality(w1, w2);
        controller.demonstrateConversion(w1, WeightUnit.GRAM);
        controller.demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        controller.demonstrateEquality(v1, v2);
        controller.demonstrateConversion(v1, VolumeUnit.MILLILITRE);
        controller.demonstrateAddition(v1, v2, VolumeUnit.LITRE);
    }
}