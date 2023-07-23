package controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controllers.templates.CreateMachineController;
import model.Item;
import model.Operation;
import model.Preset;
import model.Slot;
import model.SpecialSlot;
import model.SpecialVendingMachine;
import model.VendingMachineModel;
import states.MainMenuState;
import views.CreateSpecialMachineView;
import views.components.SetupPresetsPanel;
import views.components.StockSpecialItemsPanel;

public class CreateSpecialMachineController 
    extends CreateMachineController<CreateSpecialMachineView, SpecialVendingMachine> 
{
    public CreateSpecialMachineController(
        VendingMachineModel m, 
        CreateSpecialMachineView v
    ) {
        super(m, v);
        
        /* 1 - BASIC INFORMATION */

        view.getBasicInfoPanel().setNextButtonListener((
            String name, 
            int slotCount, 
            int slotCapacity
        ) -> {
            if (name.isBlank()) {
                view.showErrorDialog("Please enter a valid name.");
                return;
            }

            machine = new SpecialVendingMachine(name, slotCount, slotCapacity);
            view.getSetupPane().setActiveTab(1);

            view.getStockItemsPanel().setMaxStock(slotCapacity);
            view.getStockItemsPanel().setSlotCount(slotCount);
            updateSlotTable(machine.getSlots());
        });

        /* 2 - ITEM STOCK */ 

        view.getStockItemsPanel().setItemAddListener((
            int slotNo,
            String name,
            double price,
            double calories,
            String imagePath,
            boolean standalone,
            String operation,
            int stock
        ) -> {
            if (name.isBlank()) {
                view.showErrorDialog("Please enter a valid item name.");
                return;
            }

            for (int i = 0; i < machine.getSlotCount(); i++) {
                Slot slot = machine.getSlots().get(i);

                if (
                    i != slotNo && // Allow editing of selected existing slot    
                    slot.getSampleItem() != null && 
                    slot.getSampleItem().getName().equalsIgnoreCase(name)
                    
                ) {
                    view.showErrorDialog("Please enter an unused item name.");
                    return;
                }
            }

            if (imagePath == null) {
                view.showErrorDialog("Please select an item image.");
                return;                
            }

            // In case an item is already in the slot.
            machine.removeItem(slotNo);

            machine.addItem(
                slotNo, 
                name,
                price, 
                calories, 
                imagePath, 
                standalone, 
                Operation.valueOf(operation)
            );
            machine.stockItem(slotNo, stock);
            
            updateSlotTable(machine.getSlots());
        });

        view.getStockItemsPanel().setItemRemoveListener((int slotNo) -> {
            if (!machine.removeItem(slotNo)) {
                view.showErrorDialog("Cannot remove a non-existent item.");
                return;
            }

            updateSlotTable(machine.getSlots());
        });

        view.getStockItemsPanel().setNextButtonListener(() -> {
            setItemList(machine.getSlots());
            view.getSetupPane().setActiveTab(2);
        });

        view.getStockItemsPanel().setSlotSelectListener((
            int selectedSlotNo
        ) -> {
            StockSpecialItemsPanel itemsPanel = view.getStockItemsPanel();

            SpecialSlot selectedSlot = machine.getSlot(selectedSlotNo);
            Item sampleItem = selectedSlot.getSampleItem();
            
            if (sampleItem == null) {
                itemsPanel.setNameInputValue("");
                itemsPanel.setCaloriesInputValue(0.0);
                itemsPanel.setPriceInputValue(0.0);
                itemsPanel.setStockInputValue(0);
                itemsPanel.setImagePathValue(null);
                itemsPanel.setStandaloneChecked(false);
                itemsPanel.setOperationValue(0);
            } else {
                itemsPanel.setNameInputValue(sampleItem.getName());
                itemsPanel.setCaloriesInputValue(sampleItem.getCalories());
                itemsPanel.setPriceInputValue(selectedSlot.getUnitPrice());
                itemsPanel.setStockInputValue(selectedSlot.getStock());
                itemsPanel.setImagePathValue(sampleItem.getImagePath());
                itemsPanel.setStandaloneChecked(selectedSlot.isStandalone());
                itemsPanel.setOperationValue(
                    selectedSlot.getItemOperation().toString());
            }
        });

        /* 3 - SETUP PRESETS */

        view.getSetupPresetsPanel().setNextButtonListener(() -> {
            if (machine.getPresets().size() == 0) {
                view.showErrorDialog("At least one default preset should exist.");
                return;                
            }
            
            view.getSetupPane().setActiveTab(3);
        });

        view.getSetupPresetsPanel().setPresetAddListener((
            String name,
            Map<String, Integer> items,
            String operation,
            String imagePath
        ) -> {
            // TODO: handle duplicate preset names
            // TODO: perhaps look into invalid presets (e.g. only toppings) 

            if (name.isBlank()) {
                view.showErrorDialog("Please enter a valid preset name.");
                return;
            }

            if (items.size() == 0) {
                view.showErrorDialog("A preset cannot be empty.");
                return;                
            }

            machine.addPreset(new Preset(
                name, items, Operation.valueOf(operation), imagePath));
            updatePresetList(machine.getPresets());
        });

        view.getSetupPresetsPanel().setPresetRemoveListener((String name) -> {
            if (!machine.removePreset(name)) {
                view.showErrorDialog("Cannot remove a non-existent preset.");
                return;
            }

            updatePresetList(machine.getPresets());
        });

        view.getSetupPresetsPanel().setPresetSelectListener((String name) -> {
            Preset preset = machine.getPreset(name);
            assert preset != null;

            SetupPresetsPanel panel = view.getSetupPresetsPanel();
            panel.setNameInputValue(preset.getName());
            panel.setImagePathValue(preset.getImagePath());
            panel.setOperationValue(preset.getOperation().toString());

            view.getSetupPresetsPanel().updateItemMap(preset.getItems());
        });

        /* 4 - CHANGE STOCK */

        view.getStockChangePanel().setAddDenominationListener((
            double denom, 
            int quantity
        ) -> {
            machine.stockChange(denom, quantity);
            updateDenominationTable(machine.getChangeStock());
        });

        view.getStockChangePanel().setNextButtonListener(() -> {
            model.setVendingMachine(machine);
            changeState(new MainMenuState());
        });
    }

    @Override
    protected void setConstants() {
        super.setConstants();
        
        List<String> opStrings = 
            Stream.of(Operation.values())
                  .map(Operation::toString)
                  .collect(Collectors.toList());

        view.getStockItemsPanel().setOperations(opStrings);
    }

    private void updatePresetList(List<Preset> presets) {
        view.getSetupPresetsPanel().clearPresetList();

        for (Preset preset : presets) {
            view.getSetupPresetsPanel().addPreset(preset.getName());
        }
    }

    private void setItemList(List<? extends Slot> slots) {
        view.getSetupPresetsPanel().clearItemQtySelectors();

        for (Slot slot : slots) {
            if (slot.getSampleItem() == null) {
                continue;
            }

            view.getSetupPresetsPanel()
                .addItemQtySelector(slot.getSampleItem().getName());
        }
    }
}
