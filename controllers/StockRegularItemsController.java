package controllers;

import controllers.templates.StockItemsController;
import model.Item;
import model.RegularVendingMachine;
import model.Slot;
import util.View;
import views.components.StockRegularItemsPanel;

public class StockRegularItemsController 
    extends StockItemsController<RegularVendingMachine, StockRegularItemsPanel> 
{
    public StockRegularItemsController(
        RegularVendingMachine m, 
        StockRegularItemsPanel p, 
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
        int stock = stockItemsPanel.getStockInput();

        // In case an item is already in the slot.
        machine.removeItem(slotNo);

        machine.addItem(slotNo, name, price, calories, imagePath);
        machine.stockItem(slotNo, stock);
        
        updateSlotTable();
    }

    @Override
    protected void handleItemRemove() {
        int slotNo = stockItemsPanel.getSelectedSlotNo();
            
        if (!machine.removeItem(slotNo)) {
            parentView.showErrorDialog("Cannot remove a non-existent item.");
            return;
        }

        updateSlotTable();
    }

    @Override
    protected void handleSlotSelect() {
        int selectedSlotNo = stockItemsPanel.getSelectedSlotNo();
        Slot selectedSlot = machine.getSlot(selectedSlotNo);
        Item sampleItem = selectedSlot.getSampleItem();
        
        if (sampleItem == null) {
            stockItemsPanel.setItemNameInput("");
            stockItemsPanel.setCaloriesInput(0.0);
            stockItemsPanel.setPriceInput(0.0);
            stockItemsPanel.setStockInput(0);
            stockItemsPanel.setImagePathInput(null);
        } else {
            stockItemsPanel.setItemNameInput(sampleItem.getName());
            stockItemsPanel.setCaloriesInput(sampleItem.getCalories());
            stockItemsPanel.setPriceInput(selectedSlot.getUnitPrice());
            stockItemsPanel.setStockInput(selectedSlot.getStock());
            stockItemsPanel.setImagePathInput(sampleItem.getImagePath());
        }
    }
}
