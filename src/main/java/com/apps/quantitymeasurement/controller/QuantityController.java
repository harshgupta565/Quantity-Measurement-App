package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.model.IMeasurable;
import com.apps.quantitymeasurement.model.Quantity;
import com.apps.quantitymeasurement.service.QuantityService;

public class QuantityController {

    private final QuantityService service;

    // ✅ ADD THIS CONSTRUCTOR
    public QuantityController(QuantityService service) {
        this.service = service;
    }

    public <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {

        System.out.println("Are equal: " +
                service.checkEquality(q1, q2));
    }

    public <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {

        System.out.println(
                service.convert(quantity, targetUnit)
        );
    }

    public <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        System.out.println(
                service.add(q1, q2, targetUnit)
        );
    }

    public <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        System.out.println(
                service.subtract(q1, q2, targetUnit)
        );
    }
}