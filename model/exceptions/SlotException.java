package model.exceptions;

import model.Slot;

public class SlotException extends RuntimeException{
    private Slot forSlot;
    
    public SlotException(Slot forSlot) {
        this.forSlot = forSlot;
    }

    public Slot getForSlot() {
        return forSlot;
    }
}
