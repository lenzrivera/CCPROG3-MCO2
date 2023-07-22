package model;

import model.exceptions.InsufficientChangeException;
import model.exceptions.InsufficientCreditException;
import model.exceptions.InsufficientStockException;

public class RegularVendingMachine extends VendingMachine {
    private Slot selectedSlot;

    public RegularVendingMachine(String name, int slotCount, int slotCapacity) {
        super(name);

        selectedSlot = null;

        int finalSlotCount = Math.max(VendingMachine.MIN_SLOT_COUNT, slotCount);
        int finalSlotCapacity = Math.max(slotCapacity, Slot.MIN_MAX_CAPACITY);

        for (int i = 0; i < finalSlotCount; i++) {
            slots.add(new Slot(finalSlotCapacity));
        }
    }

    @Override
    public DispenseResult dispenseSelected() {
        // Do nothing if there is nothing to dispense in the first place.
        if (selectedSlot == null) {
            return null;
        }

        double total = selectedSlot.getUnitPrice();

        if (!computeChangeFor(total)) {
            throw new InsufficientChangeException();
        }

        Item dispensedItem = selectedSlot.dispenseItem();

        DispenseResult result = new DispenseResult();
        result.setName(dispensedItem.getName());
        result.addItem(dispensedItem);

        result.setChange(changeToGive.collect());
        result.setTotalPayment(total);
        
        // TODO: Add the correct message
        result.addProcessMessage("TODO");

        currentSummary.addTransaction(dispensedItem.getName(), 1, total);
        resetSelection();

        return result;
    }

    @Override
    public void resetSelection() {
        selectedSlot = null;
    }

    @Override
    public void selectItem(int slotNo) {
        Slot itemSlot = slots.get(slotNo - 1);

         if (itemSlot.getStock() == 0) {
            throw new InsufficientStockException();
        }

        if (credit.getTotal() < itemSlot.getUnitPrice()) {
            throw new InsufficientCreditException();
        }

        selectedSlot = itemSlot;       
    }
}
