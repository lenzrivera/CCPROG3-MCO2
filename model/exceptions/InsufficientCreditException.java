package model.exceptions;

/**
 * An exception thrown when there is insufficient credit available to complete 
 * a transaction.
 */
public class InsufficientCreditException extends RuntimeException {
    /**
     * The amount paid.
     */
    private double actual;
    
    /**
     * The target price of the thing being paid for.
     */
    private double target;

    /**
     * Constructs a new InsufficientChangeException.
     * @param actual the amount paid
     * @param target the target price of the thing to pay for.
     */
    public InsufficientCreditException(double actual, double target) {
        this.actual = actual;
        this.target = target;
    }

    /**
     * Gets the amount paid.
     * @return the amount paid.
     */
    public double getActual() {
        return actual;
    }

    /**
     * Gets the target price of the thing being paid for.
     * @return the target price of the thing being paid for.
     */
    public double getTarget() {
        return target;
    }
}
