package model.exceptions;

import model.Slot;

public class RemoveLastBaseException extends SlotException {
    public RemoveLastBaseException(Slot forSlot) {
        super(forSlot);
    }
}
