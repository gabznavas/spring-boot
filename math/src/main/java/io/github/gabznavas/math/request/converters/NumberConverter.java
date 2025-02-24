package io.github.gabznavas.math.request.converters;

import io.github.gabznavas.math.exceptions.UnsupportedMathOperationException;

public class NumberConverter {
    public static Double convertToDouble(String strNumber) {
        if (!isNumeric(strNumber)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return Double.parseDouble(strNumber);
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) {
            return false;
        }
        String number = strNumber.replace(",", "."); // R$5,00 USD 5.0

        // verifica se são números positivos e negativos e de 0 a 9 e depois do ponto se são de 0 a 9
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
