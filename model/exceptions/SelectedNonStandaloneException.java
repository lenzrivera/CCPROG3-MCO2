package model.exceptions;

import model.Slot;

/**
 * An exception thrown when a non-standalone base item is removed from
 * the selection in a special vending machine.
 */
public class SelectedNonStandaloneException extends SlotException {
    /**
     * Constructs a new SelectedNonStandaloneException with the specified Slot 
     * that caused the exception.
     * @param forSlot the Slot instance that caused the exception.
     */
    public SelectedNonStandaloneException(Slot forSlot) {
        super(forSlot);
    }
}
