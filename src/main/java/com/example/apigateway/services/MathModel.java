package com.example.apigateway.services;

public class MathModel {

    public enum Operation {
        sum,
        subtraction,
        multiplication,
        division,
        mean
    }

    public static Double getResultSqrt(Double numberOne) {
        return Math.sqrt(numberOne);
    }

    public static Double getResult(Operation operation, Double numberOne, Double numberTwo) {

        return switch (operation) {
            case sum -> numberOne + numberTwo;
            case subtraction -> numberOne - numberTwo;
            case multiplication -> numberOne * numberTwo;
            case division -> numberOne / numberTwo;
            case mean -> (numberOne + numberTwo) / 2;
        };

    }

    public static Double convertToDouble(String strNumber) {

        if (strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");

        if (isNumeric(number)) return Double.parseDouble(number);

        return null;
    }

    public static boolean isNumeric(String strNumber) {

        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
