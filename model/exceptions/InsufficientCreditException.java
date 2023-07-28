package model.exceptions;

public class InsufficientCreditException extends RuntimeException {
    private double actual;
    private double target;
    
    public InsufficientCreditException(double actual, double target) {
        this.actual = actual;
        this.target = target;
    }

    public double getActual() {
        return actual;
    }

    public double getTarget() {
        return target;
    }
}
