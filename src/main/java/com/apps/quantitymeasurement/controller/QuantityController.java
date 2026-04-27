package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.model.*;
import com.apps.quantitymeasurement.service.QuantityService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/quantity")
@CrossOrigin(origins = "*") // Allow requests from any frontend
public class QuantityController {

    private final QuantityService service;

    public QuantityController(QuantityService service) {
        this.service = service;
    }

    public static class ConvertRequest {
        public double value;
        public String unit;
        public String targetUnit;
        public String unitType;
    }

    public static class ArithmeticRequest {
        public double value1;
        public String unit1;
        public double value2;
        public String unit2;
        public String targetUnit;
        public String unitType;
        public String operation; // ADD, SUBTRACT, DIVIDE
    }

    @PostMapping("/convert")
    public Map<String, Object> convert(@RequestBody ConvertRequest request) {
        IMeasurable fromUnit = getUnit(request.unitType, request.unit);
        IMeasurable toUnit = getUnit(request.unitType, request.targetUnit);
        
        @SuppressWarnings({"rawtypes", "unchecked"})
        Quantity q1 = new Quantity(request.value, fromUnit);
        
        @SuppressWarnings("unchecked")
        Quantity<?> result = service.convert(q1, toUnit);
        
        Map<String, Object> response = new HashMap<>();
        response.put("result", result.getValue());
        response.put("unit", result.getUnit().getUnitName());
        return response;
    }

    @PostMapping("/arithmetic")
    public Map<String, Object> arithmetic(@RequestBody ArithmeticRequest request) {
        IMeasurable unit1 = getUnit(request.unitType, request.unit1);
        IMeasurable unit2 = getUnit(request.unitType, request.unit2);
        IMeasurable targetUnit = getUnit(request.unitType, request.targetUnit);
        
        @SuppressWarnings({"rawtypes", "unchecked"})
        Quantity q1 = new Quantity(request.value1, unit1);
        
        @SuppressWarnings({"rawtypes", "unchecked"})
        Quantity q2 = new Quantity(request.value2, unit2);
        
        Object resultValue;
        String resultUnit;

        if ("DIVIDE".equalsIgnoreCase(request.operation)) {
            // Divide returns double, not a Quantity
            @SuppressWarnings("unchecked")
            double res = service.divide(q1, q2);
            resultValue = res;
            resultUnit = "RATIO";
        } else {
            @SuppressWarnings("unchecked")
            Quantity<?> resultQuantity;
            if ("ADD".equalsIgnoreCase(request.operation)) {
                resultQuantity = service.add(q1, q2, targetUnit);
            } else if ("SUBTRACT".equalsIgnoreCase(request.operation)) {
                resultQuantity = service.subtract(q1, q2, targetUnit);
            } else {
                throw new IllegalArgumentException("Unknown operation");
            }
            resultValue = resultQuantity.getValue();
            resultUnit = resultQuantity.getUnit().getUnitName();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("result", resultValue);
        response.put("unit", resultUnit);
        return response;
    }

    private IMeasurable getUnit(String type, String unitStr) {
        if (type == null || unitStr == null) {
            throw new IllegalArgumentException("Type and unit must be provided");
        }
        switch (type.toUpperCase()) {
            case "LENGTH": return LengthUnit.valueOf(unitStr.toUpperCase());
            case "WEIGHT": return WeightUnit.valueOf(unitStr.toUpperCase());
            case "VOLUME": return VolumeUnit.valueOf(unitStr.toUpperCase());
            case "TEMPERATURE": return TemperatureUnit.valueOf(unitStr.toUpperCase());
            default: throw new IllegalArgumentException("Unknown unit type: " + type);
        }
    }
}