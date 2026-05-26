package fr.epita.calculator.services;

public class Calculator {

    public Double add(Double operand1, Double operand2){
        return operand1 + operand2;
    }
    public Double divide(Double numerator, Double divider){
        return numerator / divider;
    }
    public Double multiply(Double operand1, Double operand2){
       return operand2 * operand1;
    }
    public Double subtract(Double operand1, Double operand2){
        return operand1 - operand2;
    }
}
