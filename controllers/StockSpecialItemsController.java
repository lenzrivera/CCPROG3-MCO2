package controllers;

import controllers.templates.StockItemsController;
import model.Item;
import model.Operation;
import model.SpecialSlot;
import model.SpecialVendingMachine;
import util.View;
import views.components.StockSpecialItemsPanel;

public class StockSpecialItemsController 
    extends StockItemsController<SpecialVendingMachine, StockSpecialItemsPanel> 
{
    public StockSpecialItemsController(
        SpecialVendingMachine m, 
        StockSpecialItemsPanel p, 
        View pv
    ) {
        super(m, p, pv);
    }

    @Override
    protected void handleItemAdd() {
        if (!checkFieldValidity()) {
            return;
        }

        int slotNo = stockItemsPanel.getSelectedSlotNo();
        String name = stockItemsPanel.getItemNameInput();
        double price = stockItemsPanel.getPriceInput();
        double calories = stockItemsPanel.getCaloriesInput();
        String imagePath = stockItemsPanel.getImagePathInput();
        boolean standalone = stockItemsPanel.getStandaloneInput();
        String operation = stockItemsPanel.getOperationInput();
        int stock = stockItemsPanel.getStockInput();

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
        
        updateSlotTable();
    }

    @Override
    protected void handleSlotSelect() {
        int selectedSlotNo = stockItemsPanel.getSelectedSlotNo();
        SpecialSlot selectedSlot = machine.getSlot(selectedSlotNo);
        Item sampleItem = selectedSlot.getSampleItem();
        
        if (sampleItem == null) {
            stockItemsPanel.setItemNameInput("");
            stockItemsPanel.setCaloriesInput(0.0);
            stockItemsPanel.setPriceInput(0.0);
            stockItemsPanel.setStockInput(0);
            stockItemsPanel.setImagePathInput(null);
            stockItemsPanel.setStandaloneInput(false);
            stockItemsPanel.setOperationInput(0);
        } else {
            stockItemsPanel.setItemNameInput(sampleItem.getName());
            stockItemsPanel.setCaloriesInput(sampleItem.getCalories());
            stockItemsPanel.setPriceInput(selectedSlot.getUnitPrice());
            stockItemsPanel.setStockInput(selectedSlot.getStock());
            stockItemsPanel.setImagePathInput(sampleItem.getImagePath());
            stockItemsPanel.setStandaloneInput(selectedSlot.isStandalone());
            stockItemsPanel.setOperationInput(
                selectedSlot.getItemOperation().toString());
        }
    }
}
