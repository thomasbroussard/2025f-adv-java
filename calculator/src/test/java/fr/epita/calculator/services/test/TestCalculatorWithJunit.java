package fr.epita.calculator.services.test;

import fr.epita.calculator.services.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCalculatorWithJunit {

    @Test
    public void testDivision(){
        //given a=5, b= 5 when doing a/b the result should be equal to 1
        double a = 5;
        double b = 5;
        int expected_result = 1;

        Calculator calculator = new Calculator();

        Double result = calculator.divide(a, b);

        Assertions.assertEquals(expected_result, result, 0.01);
    }

}
