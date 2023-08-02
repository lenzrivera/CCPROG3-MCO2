package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.exceptions.DuplicateBaseException;
import model.exceptions.InsufficientStockException;
import model.exceptions.RemoveLastBaseException;
import model.exceptions.SelectedNonStandaloneException;

/**
 * This class represents a special vending machine, capable of dispensing
 * single items as well as products made from several items.
 */
public class SpecialVendingMachine extends VendingMachine<SpecialSlot> {
    /**
     * The list of presets acknowledged by the vending machine
     */
    private List<Preset> presets;

    /**
     * The map of selected slots and their respective quantities to dispense
     */
    private Map<SpecialSlot, Integer> selectedSlots;

    /**
     * The currently selected preset, if any
     */
    private Preset selectedPreset;

    /**
     * Constructs a new SpecialVendingMachine instance with the specified name, 
     * slot count, and slot capacity. Slot count falls back to a minimum of
     * MIN_SLOT_COUNT and slot capacity falls back to a minimum of 
     * Slot.MIN_MAX_CAPACITY.
     * @param name the name of the special vending machine.
     * @param slotCount the number of slots in the vending machine.
     * @param slotCapacity the capacity of each slot in the vending machine.
     */
    public SpecialVendingMachine(String name, int slotCount, int slotCapacity) {
        super(name, slotCount, slotCapacity);

        presets = new ArrayList<>();

        selectedSlots = new HashMap<>();
        selectedPreset = null;

        for (int i = 0; i < this.slotCount; i++) {
            slots.add(new SpecialSlot(this.slotCapacity));
        }
    }

    /* */

    /**
     * Returns the list of available presets for the vending machine.
     * @return the list of presets in the vending machine.
     */
    public List<Preset> getPresets() {
        return presets;
    }

    /**
     * Returns the currently selected preset in the machine.
     * @return the selected preset
     */
    public Preset getSelectedPreset() {
        return selectedPreset;
    }

    /**
     * Determines the price that selecting a preset would entail.
     * @param presetNo the number (index - 1) of the preset whose price
     * will be determined
     * @return the computed preset price
     */
    public double getPresetPrice(int presetNo) {
        double total = 0.0;

        for (var entry : presets.get(presetNo - 1).getItems().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            
            total += slots.stream()
                .filter(s -> 
                    s.getSampleItem().getName().equalsIgnoreCase(itemName))
                .findFirst().get()
                .getUnitPrice() * quantity;
        }

        return total;
    }

    /**
     * Determines the total calories that the items in a preset have.
     * @param presetNo the number (index - 1) of the preset whose calories
     * will be determined
     * @return the computed preset calories
     */
    public double getPresetCalories(int presetNo) {
        double total = 0.0;

        for (var entry : presets.get(presetNo - 1).getItems().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            
            total += slots.stream()
                .filter(s -> 
                    s.getSampleItem().getName().equalsIgnoreCase(itemName))
                .findFirst().get()
                .getSampleItem().getCalories() * quantity;
        }

        return total;
    }

    /**
     * Checks if a preset with the given name exists in the vending machine.
     * @param name the name of the preset to check.
     * @return true if the preset exists, false otherwise.
     */
    public boolean hasPreset(String name) {
        for (Preset preset : presets) {
            if (preset.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines whether the currently selected items has deviated from the
     * items in the selected preset
     * @param presetNo the number (index - 1) of the preset 
     * @return true is there is a deviation, false otherwise
     */
    public boolean hasDeviatedFrom(int presetNo) {
        for (var entry : selectedSlots.entrySet()) {
            SpecialSlot itemSlot = entry.getKey();

            String itemName = itemSlot.getSampleItem().getName();
            int itemQuantity = entry.getValue();

            // If the selected preset has been deviated from:
            if (
                !selectedPreset.getItems().containsKey(itemName) ||
                selectedPreset.getItems().get(itemName) != itemQuantity
            ) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines whether there is enough item stock to select a preset.
     * @param presetNo the number (index - 1) of the preset
     * @return true is there is enough stock, false otherwise
     */
    public boolean hasEnoughStockFor(int presetNo) {
        for (var entry : presets.get(presetNo).getItems().entrySet()) {
            String itemName = entry.getKey();
            int itemQuantity = entry.getValue();

            for (SpecialSlot itemSlot : slots) {
                if (
                    itemSlot.getSampleItem() != null &&
                    itemSlot.getSampleItem().getName().equalsIgnoreCase(itemName)
                ) {
                    if (itemSlot.getStock() < itemQuantity) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Calculates the total calories of the items selected in the vending machine.
     * @return the total calories of the selected items.
     */
    public double getTotalCalories() {
        double total = 0.0;

        for (var entry : selectedSlots.entrySet()) {
            SpecialSlot itemSlot = entry.getKey();
            total += itemSlot.getSampleItem().getCalories() * entry.getValue();
        }

        return total;
    }

    /**
     * Calculates the total price of the items selected in the vending machine.
     * @return the total price of the selected items.
     */
    public double getTotalPrice() {
        double total = 0.0;

        for (var entry : selectedSlots.entrySet()) {
            SpecialSlot itemSlot = entry.getKey();
            total += itemSlot.getUnitPrice() * entry.getValue();
        }

        return total;
    }

    /* */

    /**
     * Selects a slot to be dispensed from the vending machine. This acts akin
     * to the regular vending machine's selection method if no preset is 
     * selected. However, when a preset is selected, it acts as a way to add
     * an item to the items already pre-selected by the preset.
     * @param slotNo the number of the slot associated with the item to select
     * @throws InsufficientStockException if the slot has insufficient stock.
     * @throws SelectedNonStandaloneException if a non-standalone item is selected
     * without a preset.
     * @throws DuplicateBaseException if more than 1 of a base item will end
     * up selected
     */
    @Override
    public void addSelection(int slotNo) {
        SpecialSlot slot = getSlot(slotNo);

        if (slot.getStock() == 0) {
            throw new InsufficientStockException(credit.collect());
        }

        if (selectedPreset == null) {
            // Non-standalone items can only be selected with a preset, i.e. they
            // must be selected with at least 1 base item (it is assumed that
            // each preset has at least 1 base item).
            if (!slot.isStandalone()) {
                throw new SelectedNonStandaloneException(slot);
            }

            // Only 1 item can be selected at a time if no preset is selected.
            selectedSlots.clear();
        }

        if (!selectedSlots.containsKey(slot)) {
            selectedSlots.put(slot, 1);
        } else {
            // Only 1 of a base item can be added at a time.
            if (slot.isBase()) {
                throw new DuplicateBaseException(slot);
            }

            selectedSlots.put(slot, selectedSlots.get(slot) + 1);
        }
    }

    /**
     * Removes a single quantity of an item from the item selection.
     * @param slotNo the number of the slot associated with the item to remove
     * a selection from.
     * @throws IllegalArgumentException the item has not been selected
     * at all in the first place
     * @throws RemoveLastBaseException if the item being removed is the last
     * standalone base item in the selection
     */
    public void removeSelection(int slotNo) {
        SpecialSlot slot = getSlot(slotNo);

        if (!selectedSlots.containsKey(slot)) {
            throw new IllegalArgumentException();
        }

        int newQuantity = selectedSlots.get(slot) - 1;

        if (newQuantity == 0) {
            // When a preset is selected, at least one base item should be left
            // to allow non-standalone items to be selected (it is assumed that
            // each preset has at least 1 base item selected).
            if (selectedPreset != null && slot.isBase()) {
                boolean isLastStandaloneBase = true;

                for (SpecialSlot selectedSlot : selectedSlots.keySet()) {
                    if (
                        selectedSlot.isBase() &&
                        selectedSlot.isStandalone() &&
                        selectedSlot != slot
                    ) {
                        isLastStandaloneBase = false;
                        break;
                    }
                }

                if (isLastStandaloneBase) {
                    throw new RemoveLastBaseException(slot);
                }
            }

            selectedSlots.remove(slot);
        } else {
            selectedSlots.put(slot, newQuantity);
        }
    }

    /**
     * Dispenses the currently selected items from the vending machine. The
     * resultant DispenseResult shall take the name of the selected preset, 
     * if any, or just the name of the single selected item otherwise.
     * @return the DispenseResult object containing information about the 
     * dispensed item and transaction details.
     */
    @Override
    public DispenseResult dispenseSelection() {
        if (selectedSlots.isEmpty()) {
            return null;
        }

        // Handle payment

        double totalPayment = getTotalPrice();
        DenominationMap change = transact(totalPayment);

        // Handle dispensing and operations

        List<Item> itemsToDispense = new ArrayList<>();
        List<String> operations = new ArrayList<>();

        for (var entry : selectedSlots.entrySet()) {
            SpecialSlot itemSlot = entry.getKey();
            int qtyToDispense = entry.getValue();

            for (int i = 0; i < qtyToDispense; i++) {
                itemsToDispense.add(itemSlot.dispenseItem());
            }

            String itemName = itemSlot.getSampleItem().getName();

            operations.add(itemSlot.getOperation().getProcessMessage(itemName));

            currentSummary.addTransaction(
                itemName,
                qtyToDispense,
                itemSlot.getUnitPrice()
            );
        }

        operations.add(
            selectedPreset.getOperation()
                .getProcessMessage(selectedPreset.getName())
        );

        // Determine result name

        String resultName = itemsToDispense.get(0).getName();

        if (
            selectedPreset != null && 
            hasDeviatedFrom(presets.indexOf(selectedPreset))
        ) {
            resultName += " (Customized)";
        }

        // Clear and return

        selectedPreset = null;
        selectedSlots.clear();

        return new DispenseResult(
            resultName,
            itemsToDispense,
            operations,
            change,
            getTotalCalories(),
            totalPayment
        );
    }

    /**
     * Selects a preset from the available presets by its specified number.
     * @param presetNo the number of the preset to select.
     * @throws InsufficientStockException if there is insufficient stock for 
     * the selected preset.
     */
    public void selectPreset(int presetNo) {
        Preset preset = presets.get(presetNo - 1);

        if (!hasEnoughStockFor(presetNo)) {
            selectedSlots.clear();
            throw new InsufficientStockException(credit.collect());
        }

        for (SpecialSlot slot : slots) {
            String itemName = slot.getSampleItem().getName();
            
            if (preset.getItems().containsKey(itemName)) {
                selectedSlots.put(slot, preset.getItems().get(itemName));
            }
        }

        selectedPreset = preset;
    }

    /**
     * Deselects the currently selected preset, clearing the selected items
     * as well.
     */
    public void deselectPreset() {
        selectedPreset = null;
        selectedSlots.clear();
    }
}
