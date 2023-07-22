package controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controllers.templates.CreateMachineController;
import model.Item;
import model.Operation;
import model.Slot;
import model.SpecialSlot;
import model.SpecialVendingMachine;
import model.VendingMachineModel;
import states.MainMenuState;
import views.CreateSpecialMachineView;
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

            for (Slot slot : machine.getSlots()) {
                if (
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

        // // TODO: very many things

        // view.getSetupPresetsPanel().setNextButtonListener(() -> {
        //     // at least one default preset should be added
            
        //     view.getSetupPane().setActiveTab(3);
        // });

        // view.getSetupPresetsPanel().setPresetAddListener((
        //     String name,
        //     Map<String, Integer> items
        // ) -> {
        //     // name and items should not be empty

        //     machine.addPreset(new Preset(name, items));
        // });

        // view.getSetupPresetsPanel().setPresetRemoveListener((
        //     int presetNo
        // ) -> {
        //     if (!machine.removePreset(presetNo)) {
        //         view.showErrorDialog("Cannot remove a non-existent preset.");
        //         return;
        //     }

        //     updatePresetList(machine.getPresets());
        // });

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
}
