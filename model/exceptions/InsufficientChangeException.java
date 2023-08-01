package model.exceptions;

/**
 * An exception thrown when there is insufficient change available to complete 
 * a transaction.
 */
public class InsufficientChangeException extends RuntimeException {
    /**
     * The amount paid for which there is insufficient change.
     */
    private double forAmount;

    /**
     * Constructs a new InsufficientChangeException with the specified amount
     * that cannot be given change for.
     * @param forAmount the amount for which there is insufficient change.
     */
    public InsufficientChangeException(double forAmount) {
        this.forAmount = forAmount;
    }

    /**
     * Get the amount paid for which there is insufficient change.
     * @return the amount for which there is insufficient change.
     */
    public double getForAmount() {
        return forAmount;
    }
}