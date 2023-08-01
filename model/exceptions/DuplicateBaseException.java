package model.exceptions;

import model.Slot;

/**
 * An exception thrown when attempting to select/add the same base item twice
 * in a special vending machine.
 */
public class DuplicateBaseException extends SlotException {
    /**
     * Constructs a new DuplicateBaseException with the specified Slot that 
     * caused the exception.
     * @param forSlot the Slot instance that caused the exception.
     */
    public DuplicateBaseException(Slot forSlot) {
        super(forSlot);
    }
}