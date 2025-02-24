package io.github.gabznavas.math.controllers;

import io.github.gabznavas.math.math.SimpleMath;
import io.github.gabznavas.math.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    private SimpleMath math = new SimpleMath();

    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String strNumberOne,
            @PathVariable("numberTwo") String strNumberTwo
    ) {
        Double numberOne = NumberConverter.convertToDouble(strNumberOne);
        Double numberTwo = NumberConverter.convertToDouble(strNumberTwo);
        return math.sum(numberOne, numberTwo);
    }

    // http://localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String strNumberOne,
            @PathVariable("numberTwo") String strNumberTwo
    ) {
        Double numberOne = NumberConverter.convertToDouble(strNumberOne);
        Double numberTwo = NumberConverter.convertToDouble(strNumberTwo);
        return math.subtract(numberOne, numberTwo);
    }

    // http://localhost:8080/math/multiplication/3/5
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String strNumberOne,
            @PathVariable("numberTwo") String strNumberTwo
    ) {
        Double numberOne = NumberConverter.convertToDouble(strNumberOne);
        Double numberTwo = NumberConverter.convertToDouble(strNumberTwo);
        return math.multiply(numberOne, numberTwo);
    }

    // http://localhost:8080/math/division/3/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String strNumberOne,
            @PathVariable("numberTwo") String strNumberTwo
    ) {
        Double numberOne = NumberConverter.convertToDouble(strNumberOne);
        Double numberTwo = NumberConverter.convertToDouble(strNumberTwo);
        return math.divide(numberOne, numberTwo);
    }

    // http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String strNumberOne,
            @PathVariable("numberTwo") String strNumberTwo
    ) {
        Double numberOne = NumberConverter.convertToDouble(strNumberOne);
        Double numberTwo = NumberConverter.convertToDouble(strNumberTwo);
        return math.mean(numberOne, numberTwo);
    }

    // http://localhost:8080/math/squareRoot/81
    @RequestMapping("/square-root/{number}")
    public Double squareRoot(
            @PathVariable("number") String strNumber
    ) {
        double number = NumberConverter.convertToDouble(strNumber);
        return math.squareRoot(number);
    }
}
