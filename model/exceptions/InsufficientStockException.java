package model.exceptions;

import model.Slot;

public class InsufficientStockException extends SlotException {
    public InsufficientStockException(Slot forSlot) {
        super(forSlot);
    }
}
