package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.exceptions.InsufficientChangeException;
import model.exceptions.InsufficientCreditException;
import model.exceptions.InsufficientStockException;
import model.exceptions.SelectedStandaloneException;

public class SpecialVendingMachine extends VendingMachine<SpecialSlot> {
    private ArrayList<Preset> presets;

    private HashMap<String, Integer> itemsToDispense;

    private ArrayList<String> extraSelections;

    private Preset selectedPreset;

    public SpecialVendingMachine(String name, int slotCount, int slotCapacity) {
        super(name);
        
        presets = new ArrayList<>();
        slots = new ArrayList<>();

        itemsToDispense = new HashMap<>();
        extraSelections = new ArrayList<>();
        selectedPreset = null;

        int finalSlotCount = Math.max(VendingMachine.MIN_SLOT_COUNT, slotCount);
        int finalSlotCapacity = Math.max(slotCapacity, Slot.MIN_MAX_CAPACITY);

        for (int i = 0; i < finalSlotCount; i++) {
            slots.add(new SpecialSlot(finalSlotCapacity));
        }        
    }

    // Accessors //

    private double getItemSelectionTotal() {
        double total = 0;

        for (
            Map.Entry<String, Integer> entry : itemsToDispense.entrySet()
        ) {
            SpecialSlot itemSlot = findSlotByItemName(entry.getKey());
            total += itemSlot.getUnitPrice() * entry.getValue();
        }

        return total;
    }

    public Preset getPreset(String name) {
        for (Preset preset : presets) {
            if (preset.getName().equalsIgnoreCase(name)) {
                return preset;
            }
        }
        
        return null;
    }

    public List<Preset> getPresets() {
        return presets;
    }

    // Vending Methods //

    @Override
    public DispenseResult dispenseSelected() {
        // Do nothing if there is nothing to dispense in the first place.
        if (selectedPreset == null && itemsToDispense.isEmpty()) {
            return null;
        }

        double totalPayment = getItemSelectionTotal();

        if (!computeChangeFor(totalPayment)) {
            throw new InsufficientChangeException();
        }

        DispenseResult result = new DispenseResult();

        for (
           Map.Entry<String, Integer> entry : itemsToDispense.entrySet()
        ) {
            String itemName = entry.getKey();
            int qtyToDispense = entry.getValue();

            SpecialSlot itemSlot = findSlotByItemName(itemName);

            for (int i = 0; i < qtyToDispense; i++) {
                result.addItem(itemSlot.dispenseItem());
            }

            result.addProcessMessage(
                itemSlot.getItemOperation().getProcessMessage(itemName));

            currentSummary.addTransaction(
                itemName, qtyToDispense, itemSlot.getUnitPrice());
        }

        if (selectedPreset == null) {
            result.setName(result.getItems().get(0).getName());
        } else if (extraSelections.size() == 0) {
            result.setName(selectedPreset.getName());
        } else {
            String name = selectedPreset.getName() + "(w/ Additional ";

            for (int i = 0; i < extraSelections.size(); i++) {
                name += extraSelections.get(i);

                if (i != extraSelections.size() - 1) {
                    name += ", ";
                }
            }

            name += ")";

            result.setName(name);
        }

        Operation presetOp = selectedPreset.getOperation();
        result.addProcessMessage(
            presetOp.getProcessMessage(selectedPreset.getName()));
        result.addProcessMessage("Done!");

        result.setTotalPayment(totalPayment);
        resetSelection();
        
        return result;
    }

    private SpecialSlot findSlotByItemName(String name) {
        for (SpecialSlot slot : slots) {
             if (
                slot.getStock() > 0 && 
                slot.getSampleItem().getName().equalsIgnoreCase(name)
            ) {
                return slot;
            }           
        }

        return null;
    }

    @Override
    public void resetSelection() {
        itemsToDispense.clear();
        extraSelections.clear();
        selectedPreset = null;
    }

    @Override
    public void selectItem(int slotNo) {
        SpecialSlot itemSlot = slots.get(slotNo - 1);

        if (itemSlot.getStock() == 0) {
            throw new InsufficientStockException();
        }

        if (!itemSlot.isStandalone()) {
            throw new SelectedStandaloneException();
        }

        if (credit.getTotal() < getItemSelectionTotal()) {
            throw new InsufficientCreditException();
        }

        Item sample = itemSlot.getSampleItem();

        // Only one distinct item can be selected/dispensed should it be
        // selected and not item presets.
        if (selectedPreset == null) {
            itemsToDispense.clear();
        } else {
            extraSelections.add(sample.getName());
        }

        if (itemsToDispense.get(sample.getName()) == null) {
            itemsToDispense.put(
                sample.getName(), itemsToDispense.get(sample.getName()) + 1);
        } else {
            itemsToDispense.put(sample.getName(), 1);
        }
    }

    public void selectPreset(int presetNo) {        
        Preset preset = presets.get(presetNo - 1);

        double totalSoFar = 0;

        for (
            Map.Entry<String, Integer> entry : 
                selectedPreset.getItems().entrySet()
        ) {
            String itemName = entry.getKey();
            int itemQuantity = entry.getValue();

            SpecialSlot itemSlot = findSlotByItemName(itemName);

            if (itemSlot == null || itemSlot.getStock() < itemQuantity) {
                resetSelection();
                throw new InsufficientStockException();
            }

            totalSoFar += itemSlot.getUnitPrice();

            if (credit.getTotal() < totalSoFar) {
                resetSelection();
                throw new InsufficientCreditException();
            }

            itemsToDispense.put(itemName, itemQuantity);
        }

        selectedPreset = preset;
    }

    // Maintenance Methods //

    public boolean addItem(
        int slotNo, 
        String name, 
        double price, 
        double calories,
        String imagePath,
        boolean standalone,
        Operation operation
    ) {
        SpecialSlot slot = slots.get(slotNo - 1);

        if (slot.getSampleItem() != null) {
            return false;
        }

        slot.assignToItem(name, price, calories, imagePath, standalone, operation);
        return true;
    }

    public boolean addPreset(Preset preset) {
        for (
            Map.Entry<String, Integer> entry : 
                selectedPreset.getItems().entrySet()
        ) {
            if (findSlotByItemName(entry.getKey()) == null) {
                return false;
            }
        }
        
        return presets.add(preset);
    }

    public boolean removePreset(String presetName) {
        return presets.removeIf(p -> p.getName().equalsIgnoreCase(presetName));
    }
}
