package controllers.templates;

import model.Slot;
import model.VendingMachine;
import util.View;
import views.components.StockItemsPanel;

public abstract class StockItemsController<
    T extends VendingMachine<?>, 
    U extends StockItemsPanel> 
{
    protected View parentView;

    protected T machine;
    protected U stockItemsPanel;
    
    public StockItemsController(T m, U p, View pv) {
        parentView = pv;

        machine = m;
        stockItemsPanel = p;

        setListeners();
        updateSlotTable();

        // Select the first slot by default.
        handleSlotSelect();
    }

    // TODO: proper "simulation"
    protected abstract void handleItemAdd();

    protected abstract void handleSlotSelect();

    protected boolean checkFieldValidity() {
        int slotIndex = stockItemsPanel.getSelectedSlotNo() - 1;
        String name = stockItemsPanel.getItemNameInput();
        String imagePath = stockItemsPanel.getImagePathInput();

        if (name.isBlank()) {
            parentView.showErrorDialog("Please enter a valid item name.");
            return false;
        }

        for (int i = 0; i < machine.getSlotCount(); i++) {
            Slot slot = machine.getSlots().get(i);

            if (
                i != slotIndex && 
                slot.getSampleItem() != null && 
                slot.getSampleItem().getName().equalsIgnoreCase(name)
                
            ) {
                parentView.showErrorDialog("Please enter an unused item name.");
                return false;
            }
        }

        if (imagePath == null) {
            parentView.showErrorDialog("Please select an item image.");
            return false;                
        }

        return true;
    }

    protected void setListeners() {
        stockItemsPanel.setItemAddListener(e -> {
            handleItemAdd();
        });

        stockItemsPanel.setSlotSelectListener(e -> {
            handleSlotSelect();
        });

         stockItemsPanel.setItemRemoveListener(e -> {
            int slotNo = stockItemsPanel.getSelectedSlotNo();
            
            if (!machine.removeItem(slotNo)) {
                parentView.showErrorDialog("Cannot remove a non-existent item.");
                return;
            }

            updateSlotTable();
        });
    }

    /* */

    public void updateSlotTable() {
        for (int i = 0; i < machine.getSlots().size(); i++) {
            String name = (machine.getSlot(i + 1).getSampleItem() == null) 
                ? "[empty]" 
                : machine.getSlot(i + 1).getSampleItem().getName();

            stockItemsPanel.setSlotNumberCell(i + 1, i + 1);
            stockItemsPanel.setItemNameCell(i + 1, name);
        }
    }
}
