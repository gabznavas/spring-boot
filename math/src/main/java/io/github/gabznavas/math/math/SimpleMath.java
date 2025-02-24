package io.github.gabznavas.math.math;

import io.github.gabznavas.math.exceptions.UnsupportedMathOperationException;

public class SimpleMath {
    public Double sum(Double n1, Double n2) {
        return n1 + n2;
    }

    public Double subtract(Double n1, Double n2) {
        return n1 - n2;
    }

    public Double multiply(Double n1, Double n2) {
        return n1 * n2;
    }

    public Double divide(Double n1, Double n2) {
        if (n2 == 0) {
            throw new UnsupportedMathOperationException("0 is not a valid divisor.");
        }
        return n1 / n2;
    }

    public Double mean(Double n1, Double n2) {
        return (n1 + n2) / 2;
    }

    public Double squareRoot(Double n) {
        if (n < 0) {
            throw new UnsupportedMathOperationException("Please set a zero or positive number");
        }
        return Math.sqrt(n);
    }
}
