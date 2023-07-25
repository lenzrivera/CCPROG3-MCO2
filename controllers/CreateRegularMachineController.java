package controllers;

import controllers.templates.CreateMachineController;
import model.Item;
import model.RegularVendingMachine;
import model.Slot;
import model.VendingMachineModel;
import states.MainMenuState;
import views.CreateRegularMachineView;
import views.components.StockItemsPanel;

public class CreateRegularMachineController 
    extends CreateMachineController<CreateRegularMachineView, RegularVendingMachine> 
{
    public CreateRegularMachineController(
        VendingMachineModel m, 
        CreateRegularMachineView v
    ) {
        super(m, v);

        view.setExitButtonListener(e -> {
            // Exit without saving anything.
            changeState(new MainMenuState());
        });

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

            machine = new RegularVendingMachine(name, slotCount, slotCapacity);
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

            machine.addItem(slotNo, name, price, calories, imagePath);
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
            StockItemsPanel itemsPanel = view.getStockItemsPanel();

            Slot selectedSlot = machine.getSlot(selectedSlotNo);
            Item sampleItem = selectedSlot.getSampleItem();
            
            if (sampleItem == null) {
                itemsPanel.setNameInputValue("");
                itemsPanel.setCaloriesInputValue(0.0);
                itemsPanel.setPriceInputValue(0.0);
                itemsPanel.setStockInputValue(0);
                itemsPanel.setImagePathValue(null);
            } else {
                itemsPanel.setNameInputValue(sampleItem.getName());
                itemsPanel.setCaloriesInputValue(sampleItem.getCalories());
                itemsPanel.setPriceInputValue(selectedSlot.getUnitPrice());
                itemsPanel.setStockInputValue(selectedSlot.getStock());
                itemsPanel.setImagePathValue(sampleItem.getImagePath());
            }
        });

        /* 3 - CHANGE STOCK */

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
}