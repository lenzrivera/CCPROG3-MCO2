package model.exceptions;

public class InsufficientChangeException extends RuntimeException {
    private double forAmount;
    
    public InsufficientChangeException(double forAmount) {
        this.forAmount = forAmount;
    }

    public double getForAmount() {
        return forAmount;
    }
}
