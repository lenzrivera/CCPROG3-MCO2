package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Operation;
import model.Preset;
import model.Slot;
import model.SpecialVendingMachine;
import util.View;
import views.components.SetupPresetsPanel;

// TODO: handle presets with deleted items

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

        // Preset preset = machine.getPresets().get(0);
        // assert preset != null;

        // setupPresetsPanel.setNameInputValue(preset.getName());
        // setupPresetsPanel.setImagePathValue(preset.getImagePath());
        // setupPresetsPanel.setOperationValue(preset.getOperation().toString());

        // setupPresetsPanel.updateItemMap(preset.getItems());
    }

    private void setListeners() {
        setupPresetsPanel.setPresetAddListener(e -> {
            // TODO: handle duplicate preset names
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

            if (items.size() == 0) {
                parentView.showErrorDialog("A preset cannot have no items.");
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
