package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.model.IMeasurable;
import com.apps.quantitymeasurement.model.Quantity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import org.springframework.stereotype.Service;

@Service
public class QuantityService {

    private final IQuantityMeasurementRepository repository;

    // ✅ Constructor Injection (VERY IMPORTANT)
    public QuantityService(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    public <U extends IMeasurable> boolean checkEquality(
            Quantity<U> q1, Quantity<U> q2) {

        boolean result = q1.equals(q2);

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q1.getUnit().getUnitName(),
                "EQUALITY",
                result ? 1 : 0
        ));

        return result;
    }

    public <U extends IMeasurable> Quantity<U> convert(
            Quantity<U> quantity, U targetUnit) {

        Quantity<U> result = quantity.convertTo(targetUnit);

        repository.save(new QuantityMeasurementEntity(
                quantity.getValue(),
                quantity.getUnit().getUnitName(),
                "CONVERT",
                result.getValue()
        ));

        return result;
    }

    public <U extends IMeasurable> Quantity<U> add(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        Quantity<U> result = q1.add(q2, targetUnit);

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q1.getUnit().getUnitName(),
                "ADD",
                result.getValue()
        ));

        return result;
    }

    public <U extends IMeasurable> Quantity<U> subtract(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        Quantity<U> result = q1.subtract(q2, targetUnit);

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q1.getUnit().getUnitName(),
                "SUBTRACT",
                result.getValue()
        ));

        return result;
    }

    public <U extends IMeasurable> double divide(
            Quantity<U> q1, Quantity<U> q2) {

        double result = q1.divide(q2);

        repository.save(new QuantityMeasurementEntity(
                q1.getValue(),
                q1.getUnit().getUnitName(),
                "DIVIDE",
                result
        ));

        return result;
    }
}