package model.exceptions;

import model.Slot;

public class SelectedNonStandaloneException extends SlotException {
    public SelectedNonStandaloneException(Slot forSlot) {
        super(forSlot);
    }
}
