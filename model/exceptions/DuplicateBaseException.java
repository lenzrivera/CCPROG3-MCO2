package model.exceptions;

import model.Slot;

public class DuplicateBaseException extends SlotException {
    public DuplicateBaseException(Slot forSlot) {
        super(forSlot);
    }
}
