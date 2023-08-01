package model.exceptions;

/**
 * An exception thrown when there is the available denominations in the machine
 * cannot produce an exact change to complete a transaction.
 */
public class MissingChangeException extends RuntimeException {
    /**
     * The amount paid for which there is missing change.
     */
    private double forAmount;

    /**
     * Constructs a new MissingChangeException with the specified amount
     * that cannot be given change for.
     * @param forAmount the amount for which there is missing change.
     */
    public MissingChangeException(double forAmount) {
        this.forAmount = forAmount;
    }

    /**
     * Get the amount paid for which there is missing change.
     * @return the amount for which there is missing change.
     */
    public double getForAmount() {
        return forAmount;
    }
}