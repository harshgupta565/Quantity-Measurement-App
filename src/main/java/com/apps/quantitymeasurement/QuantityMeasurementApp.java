package com.apps.quantitymeasurement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuantityMeasurementApp {

    private static double readDouble(BufferedReader reader, String prompt) throws IOException {

        while (true) {
            System.out.print(prompt);
            String input = reader.readLine();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static LengthUnit readUnit(BufferedReader reader, String prompt) throws IOException {

        while (true) {
            System.out.print(prompt);
            String input = reader.readLine().toUpperCase();

            try {
                return LengthUnit.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid unit. Enter FEET, INCHES, YARDS or CENTIMETERS");
            }
        }
    }

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("---- Quantity Measurement App ----");

            double value1 = readDouble(reader, "Enter value 1: ");
            LengthUnit unit1 = readUnit(reader, "Enter unit 1 (FEET/INCHES/YARDS/CENTIMETERS): ");

            double value2 = readDouble(reader, "Enter value 2: ");
            LengthUnit unit2 = readUnit(reader, "Enter unit 2 (FEET/INCHES/YARDS/CENTIMETERS): ");

            QuantityLength q1 = new QuantityLength(value1, unit1);
            QuantityLength q2 = new QuantityLength(value2, unit2);

            // Equality check
            boolean result = q1.equals(q2);
            System.out.println("Are the two measurements equal? " + result);

            // UC6 Addition
            QuantityLength sum = q1.add(q2);

           System.out.println("Addition Result: " + sum);

        } catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }
}