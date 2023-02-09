package com.example.apigateway.models;

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

        switch (operation) {
            case sum: return numberOne + numberTwo;
            case subtraction: return numberOne - numberTwo;
            case multiplication: return numberOne * numberTwo;
            case division: return numberOne / numberTwo;
            case mean: return (numberOne + numberTwo) / 2;
        }

        return null;
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
