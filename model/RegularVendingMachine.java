package model;

import java.util.Arrays;

import model.exceptions.InsufficientStockException;

public class RegularVendingMachine extends VendingMachine<Slot> {
    private Slot selectedSlot;

    public RegularVendingMachine(String name, int slotCount, int slotCapacity) {
        super(name, slotCount, slotCapacity);

        selectedSlot = null;

        for (int i = 0; i < this.slotCount; i++) {
            slots.add(new Slot(this.slotCapacity));
        }
    }

    @Override
    public void addSelection(int slotNo) {
        Slot slot = getSlot(slotNo);

        if (slot.getStock() == 0) {
            throw new InsufficientStockException(slot);
        }

        selectedSlot = slot;
    }

    @Override
    public DispenseResult dispenseSelection() {
        if (selectedSlot == null) {
            return null;
        }
        
        double totalPayment = selectedSlot.getUnitPrice();
        DenominationMap change = transact(totalPayment);

        Item dispensedItem = selectedSlot.dispenseItem();
        currentSummary.addTransaction(dispensedItem.getName(), 1, totalPayment);
        selectedSlot = null;

        return new DispenseResult(
            dispensedItem.getName(),
            Arrays.asList(dispensedItem),
            Arrays.asList("Dispensed " + dispensedItem.getName()),
            change,
            totalPayment,
            dispensedItem.getCalories()
        );
    }
}
