package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Operation;
import model.Preset;
import model.Slot;
import model.SpecialSlot;
import model.SpecialVendingMachine;
import util.View;
import views.components.SetupPresetsPanel;

public class SetupPresetsController {
    private View parentView;

    private SpecialVendingMachine machine;
    private SetupPresetsPanel setupPresetsPanel;

    public SetupPresetsController(
        SpecialVendingMachine machine, 
        SetupPresetsPanel setupPresetsPanel, 
        View parentView
    ) {
        this.parentView = parentView;

        this.machine = machine;
        this.setupPresetsPanel = setupPresetsPanel;

        setListeners();
        setItemList(this.machine.getSlots());
        updatePresetList();
    }

    private void setListeners() {
        List<String> opStrings = 
            Stream.of(Operation.values())
                  .map(Operation::toString)
                  .collect(Collectors.toList());
        setupPresetsPanel.setOperations(opStrings);

        setupPresetsPanel.setPresetAddListener(e -> {
            // TODO: perhaps look into invalid presets (e.g. only toppings) 

            int presetIndex = setupPresetsPanel.getSelectedPresetIndex();
            String name = setupPresetsPanel.getNameInput();
            Map<String, Integer> items = setupPresetsPanel.getItemMapping();
            String operation = setupPresetsPanel.getOperationInput();
            String imagePath = setupPresetsPanel.getImagePath();

            if (name.isBlank()) {
                parentView.showErrorDialog("Please enter a valid preset name.");
                return;
            }

            for (int i = 0; i < machine.getPresets().size(); i++) {
                Preset preset = machine.getPresets().get(i);
    
                if (
                    i != presetIndex && 
                    preset != null && 
                    preset.getName().equalsIgnoreCase(name)    
                ) {
                    parentView.showErrorDialog(
                        "Please enter an unused preset name.");
                    return;
                }
            }

            if (items.size() == 0) {
                parentView.showErrorDialog("A preset cannot have no items.");
                return;                
            }

            boolean hasBaseItem = false;

            for (SpecialSlot slot : machine.getSlots()) {
                if (
                    slot.isBase() && slot.isStandalone() && 
                    items.containsKey(slot.getSampleItem().getName())
                ) {
                    hasBaseItem = true;
                    break;
                }
            }

            if (!hasBaseItem) {
                parentView.showErrorDialog(
                    "A preset cannot have no standalone base items.");
                return;                   
            }

            if (imagePath.isBlank()) {
                parentView.showErrorDialog("Please select a preset image.");
                return;                
            }

            Preset newPreset = new Preset(
                name, items, Operation.valueOf(operation), imagePath);

            if (presetIndex == -1) {
                machine.addPreset(newPreset);
            } else {
                machine.getPresets().set(presetIndex, newPreset);
            }

            updatePresetList();
        });

        setupPresetsPanel.setPresetRemoveListener(e -> {
            String name = setupPresetsPanel.getNameInput();

            if (!machine.removePreset(name)) {
                parentView.showErrorDialog("Cannot remove a non-existent preset.");
                return;
            }

            updatePresetList();
        });

        setupPresetsPanel.setPresetSelectListener(e -> {
            int selectedIndex = setupPresetsPanel.getSelectedPresetIndex();
            
            if (selectedIndex == -1) {
                setupPresetsPanel.setNameInputValue("");
                setupPresetsPanel.setImagePathValue("");
                setupPresetsPanel.setOperationValue(0);
                setupPresetsPanel.updateItemMap(new HashMap<>());
                return;
            }

            Preset preset = machine.getPresets().get(selectedIndex);
            setupPresetsPanel.setNameInputValue(preset.getName());
            setupPresetsPanel.setImagePathValue(preset.getImagePath());
            setupPresetsPanel.setOperationValue(preset.getOperation().toString());

            setupPresetsPanel.updateItemMap(preset.getItems());
        });       
    }

    /* */

    private void setItemList(List<? extends Slot> slots) {
        setupPresetsPanel.clearItemQtySelectors();

        for (Slot slot : slots) {
            if (slot.getSampleItem() == null) {
                continue;
            }

            setupPresetsPanel.addItemQtySelector(slot.getSampleItem().getName());
        }
    }

    private void updatePresetList() {
        setupPresetsPanel.clearPresetList();

        for (Preset preset : machine.getPresets()) {
            setupPresetsPanel.addPreset(preset.getName());
        }
    }
}
