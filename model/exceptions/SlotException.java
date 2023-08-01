package model.exceptions;

import model.Slot;

/**
 * An abstract exception that is thrown when there is an issue pertaining
 * to a particular slot.
 */
public abstract class SlotException extends RuntimeException{
    /**
     * The relevant slot in the exception.
     */
    private Slot forSlot;
    
    /**
     * Constructs a new SlotException with the specified Slot 
     * that caused the exception.
     * @param forSlot the Slot instance that caused the exception.
     */
    public SlotException(Slot forSlot) {
        this.forSlot = forSlot;
    }

    /**
     * Gets the slot which caused or is relevant to the exception.
     * @return the relevant slot
     */
    public Slot getForSlot() {
        return forSlot;
    }
}
