package model.exceptions;

import model.Slot;

/**
 * An exception thrown when there is insufficient stock available to complete 
 * a transaction.
 */
public class InsufficientStockException extends SlotException {
    /**
     * Constructs a new InsufficientStockException with the specified Slot that 
     * caused the exception.
     * @param forSlot the Slot instance that caused the exception.
     */
    public InsufficientStockException(Slot forSlot) {
        super(forSlot);
    }
}
