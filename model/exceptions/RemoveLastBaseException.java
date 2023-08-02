package model.exceptions;

import model.Slot;

/**
 * An exception thrown when the last standalone base item is removed from
 * the selection in a special vending machine.
 */
public class RemoveLastBaseException extends SlotException {
    /**
     * Constructs a new RemoveLastBaseException with the specified Slot that 
     * caused the exception.
     * @param forSlot the Slot instance that caused the exception.
     */
    public RemoveLastBaseException(Slot forSlot) {
        super(forSlot);
    }

    @Override
    public String getMessage() {
        String itemName = getForSlot().getSampleItem().getName();
        return "Cannot remove the last instance of " + itemName + ".";
    }
}
