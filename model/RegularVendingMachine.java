package model;

import java.util.Arrays;

import model.exceptions.InsufficientStockException;

/**
 * This class represents a regular vending machine, capable of only dispensing
 * single items.
 */
public class RegularVendingMachine extends VendingMachine<Slot> {
    /**
     * The currently selected slot to dispense.
     */
    private Slot selectedSlot;

    /**
     * Constructs a new RegularVendingMachine instance with the specified 
     * name, slot count, and slot capacity.
     * @param name the name of the vending machine.
     * @param slotCount the total number of slots in the vending machine.
     * @param slotCapacity the maximum capacity of each slot.
     */
    public RegularVendingMachine(String name, int slotCount, int slotCapacity) {
        super(name, slotCount, slotCapacity);

        selectedSlot = null;

        for (int i = 0; i < this.slotCount; i++) {
            slots.add(new Slot(this.slotCapacity));
        }
    }

    /**
     * Selects a slot to be dispensed from the vending machine.
     * @param slotNo the slot number of the item to select
     * @throws InsufficientStockException if the selected slot is out of stock.
     */
    @Override
    public void addSelection(int slotNo) throws InsufficientStockException {
        Slot slot = getSlot(slotNo);

        if (slot.getStock() == 0) {
            throw new InsufficientStockException(slot);
        }

        selectedSlot = slot;
    }

    /**
     * Dispenses the currently selected item from the vending machine.
     * @return the DispenseResult object containing information about the 
     * dispensed item and transaction details.
     */
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
