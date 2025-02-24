package io.github.gabznavas.math.controllers;

import io.github.gabznavas.math.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne")
            String numberOne,
            @PathVariable("numberTwo")
            String numberTwo
    ) {
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne")
            String numberOne,
            @PathVariable("numberTwo")
            String numberTwo
    ) {
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/multiplication/3/5
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    // http://localhost:8080/math/division/3/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String strNumberOne,
            @PathVariable("numberTwo") String strNumberTwo
    ) {
        Double numberOne = convertToDouble(strNumberOne);
        Double numberTwo = convertToDouble(strNumberTwo);
        if (numberTwo == 0) {
            throw new UnsupportedMathOperationException("0 is not a valid divisor.");
        }
        return numberOne / numberTwo;
    }

    // http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String strNumberOne,
            @PathVariable("numberTwo") String strNumberTwo
    ) {
        Double numberOne = convertToDouble(strNumberOne);
        Double numberTwo = convertToDouble(strNumberTwo);
        return (numberOne + numberTwo) / 2;
    }

    // http://localhost:8080/math/squareRoot/81
    @RequestMapping("/square-root/{number}")
    public Double squareRoot(
            @PathVariable("number") String strNumber
    ) {
        double number = convertToDouble(strNumber);
        if (number < 0) {
            throw new UnsupportedMathOperationException("Please set a zero or positive number");
        }
        return Math.sqrt(number);
    }

    private Double convertToDouble(String strNumber) {
        if (!isNumeric(strNumber)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return Double.parseDouble(strNumber);
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) {
            return false;
        }
        String number = strNumber.replace(",", "."); // R$5,00 USD 5.0

        // verifica se são números positivos e negativos e de 0 a 9 e depois do ponto se são de 0 a 9
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
