package controllers.templates;

import java.util.List;

import model.Denomination;
import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import util.Controller;
import views.components.StockItemsPanel;
import views.templates.CreateMachineView;

// TODO: CreateMachineView may be simplified to remove generics

public abstract class CreateMachineController<
    T extends CreateMachineView<?>> extends Controller 
{
    protected VendingMachineModel model;
    protected T view;

    public CreateMachineController(VendingMachineModel model, T view) {
        this.model = model;
        this.view = view;

        setConstants();
        setListeners();
    }

    /* */

    protected abstract void setListeners();
    
    protected void setConstants() {
        view.getBasicInfoPanel()
            .getContent()
            .setMinSlotCount(VendingMachine.MIN_SLOT_COUNT);
        view.getBasicInfoPanel()
            .getContent()
            .setMinSlotCapacity(Slot.MIN_MAX_CAPACITY);
        view.getStockChangePanel()
            .getContent()    
            .setDenominations(Denomination.getDoubleValues());
    }

    /* */

    protected boolean checkFieldValidity(VendingMachine<?> machine) {
        StockItemsPanel setItemsPanel = view.getStockItemsPanel().getContent();

        int slotIndex = setItemsPanel.getSelectedSlotNo() - 1;
        String name = setItemsPanel.getItemNameInput();
        double price = setItemsPanel.getPriceInput();
        String imagePath = setItemsPanel.getImagePathInput();

        if (name.isBlank()) {
            view.showErrorDialog("Please enter a valid item name.");
            return false;
        }

        for (int i = 0; i < machine.getSlotCount(); i++) {
            Slot slot = machine.getSlots().get(i);

            if (
                i != slotIndex && 
                slot.getSampleItem() != null && 
                slot.getSampleItem().getName().equalsIgnoreCase(name)
                
            ) {
                view.showErrorDialog("Please enter an unused item name.");
                return false;
            }
        }

        if (Denomination.toEnum(price) == null) {
            view.showErrorDialog("Please enter a valid price.");
            return false;
        }

        if (imagePath == null) {
            view.showErrorDialog("Please select an item image.");
            return false;                
        }

        return true;
    }

    /**
     * Updates the stock change panel's denomination table with the provided 
     * DenominationMap.
     * @param denomMap the DenominationMap containing the denominations and 
     * quantities.
     */
    protected void updateDenominationTable(DenominationMap denomMap) {
        int count = 1;

        for (var entry : denomMap.getQuantityMap().entrySet()) {
            view.getStockChangePanel()
                .getContent()
                .getDenomTable()
                .setDenominationCell(count, entry.getKey().getValue());
            view.getStockChangePanel()
                .getContent()
                .getDenomTable()
                .setQuantityCell(count, entry.getValue());
            
            count += 1;
        }
    }

    public void updateSlotTable(List<? extends Slot> slots) {
        StockItemsPanel setItemsPanel = view.getStockItemsPanel().getContent();
        
        for (int i = 0; i < slots.size(); i++) {
            String name = (slots.get(i).getSampleItem() == null) 
                ? "[empty]" 
                : slots.get(i).getSampleItem().getName();

            setItemsPanel.setSlotNumberCell(i + 1, i + 1);
            setItemsPanel.setItemNameCell(i + 1, name);
        }
    }
}
