package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.exceptions.DuplicateBaseException;
import model.exceptions.InsufficientStockException;
import model.exceptions.RemoveLastBaseException;
import model.exceptions.SelectedNonStandaloneException;

public class SpecialVendingMachine extends VendingMachine<SpecialSlot> {
    private List<Preset> presets;

    private Map<SpecialSlot, Integer> selectedSlots;
    private Preset selectedPreset;
   
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

    public List<Preset> getPresets() {
        return presets;
    }

    public boolean hasPreset(String name) {
        for (Preset preset : presets) {
            if (preset.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }
    
    public double getTotalCalories() {
        double total = 0.0;

        for (var entry : selectedSlots.entrySet()) {
            Slot itemSlot = entry.getKey();
            total += itemSlot.getSampleItem().getCalories() * entry.getValue();
        }

        return total;
    }

    public double getTotalPrice() {
        double total = 0.0;

        for (var entry : selectedSlots.entrySet()) {
            Slot itemSlot = entry.getKey();
            total += itemSlot.getUnitPrice() * entry.getValue();
        }

        return total;
    }

    /* */

    @Override
    public void addSelection(int slotNo) {
        SpecialSlot slot = getSlot(slotNo);

        if (slot.getStock() == 0) {
            throw new InsufficientStockException(slot);
        }

        if (selectedPreset == null) {
            // Non-standalone items can only be selected w/ a preset, i.e. they
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
            selectedSlots.put(slot, selectedSlots.get(slot) - 1);
        }
    }

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
        operations.add("Done!");

        // Determine result name

        String resultName = itemsToDispense.get(0).getName();
        
        if (selectedPreset != null) {
            for (var entry : selectedSlots.entrySet()) {
                SpecialSlot itemSlot = entry.getKey();

                String itemName = itemSlot.getSampleItem().getName();
                int itemQuantity = entry.getValue();

                // If the select preset has been deviated from:
                if (
                    !selectedPreset.getItems().containsKey(itemName) || 
                    selectedPreset.getItems().get(itemName) != itemQuantity
                ) {
                    resultName += " (Customized)";
                    break;
                }
            }
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

    public void selectPreset(int presetNo) {
        Preset preset = presets.get(presetNo - 1);

        for (var entry : preset.getItems().entrySet()) {
            String itemName = entry.getKey();
            int itemQuantity = entry.getValue();
            
            SpecialSlot itemSlot = findSlotByItemName(itemName);

            if (itemSlot.getStock() < itemQuantity) {
                selectedSlots.clear();
                throw new InsufficientStockException(itemSlot);
            }

            selectedSlots.put(itemSlot, itemQuantity);
        }

        selectedPreset = preset;
    }

    public void deselectPreset() {
        selectedPreset = null;
        selectedSlots.clear();
    }

    /* */

    private SpecialSlot findSlotByItemName(String name) {
        for (SpecialSlot slot : slots) {
            if (
                slot.getSampleItem() != null && 
                slot.getSampleItem().getName().equalsIgnoreCase(name)
            ) {
                return slot;
            }
        }

        return null;
    }
}
