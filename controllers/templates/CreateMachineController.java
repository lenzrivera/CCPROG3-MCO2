package controllers.templates;

import java.util.List;

import model.Denomination;
import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import util.Controller;
import views.components.SetupItemsPanel;
import views.templates.CreateMachineView;

/**
 * This abstract controller class contains the common code for managing
 * the creation of vending machines.
 * @param <T> the type of CreateMachineView associated with this controller.
 */
public abstract class CreateMachineController<
    T extends CreateMachineView<?>> extends Controller 
{
    /**
     * The VendingMachineModel for the whole simulator.
     */
    protected VendingMachineModel model;
    
    /**
     * The view associated with the controller.
     */
    protected T view;

    /**
     * Constructs a new CreateMachineController with the provided 
     * VendingMachineModel and view.
     * @param model the VendingMachineModel associated with the controller
     * @param view the view associated with the controller
     */
    public CreateMachineController(VendingMachineModel model, T view) {
        this.model = model;
        this.view = view;

        setConstants();
        setListeners();
    }

    /**
     * Sets up the event listeners for the view.
     */
    protected abstract void setListeners();
    
    /**
     * Sets the constants for the view, such as minimum slot count, minimum 
     * slot capacity, and available denominations for money management.
     */
    protected void setConstants() {
        view.getBasicInfoPanel()
            .getContent()
            .setMinSlotCount(VendingMachine.MIN_SLOT_COUNT);
        view.getBasicInfoPanel()
            .getContent()
            .setMinSlotCapacity(Slot.MIN_MAX_CAPACITY);
        view.getManageMoneyPanel()
            .getContent()    
            .setDenominations(Denomination.getDoubleValues());
    }

    /**
     * Checks the validity of the input fields for setting an item in the
     * vending machine.
     * @param machine the vending machine instance to check against in
     * validating the fields.
     * @return true if all fields are valid, false otherwise.
     */
    protected boolean checkFieldValidity(VendingMachine<?> machine) {
        SetupItemsPanel setItemsPanel = view.getSetItemsPanel().getContent();

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

        if (!Denomination.isValidPrice(price)) {
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
     * Updates the change stock panel's denomination table with the provided 
     * DenominationMap.
     * @param denomMap the DenominationMap containing the denominations and 
     * quantities.
     */
    protected void updateDenominationTable(DenominationMap denomMap) {
        view.getManageMoneyPanel().getContent().clearTableCells();
        
        int i = 0;
        
        for (var entry : denomMap.getQuantityMap().entrySet()) {
            view.getManageMoneyPanel()
                .getContent()
                .setDenomCell(i, entry.getKey().getValue());
            view.getManageMoneyPanel()
                .getContent()
                .setQuantityCell(i, entry.getValue());
            
            i += 1;
        }
    }

    /**
     * Updates the set item panel's slot table with the provided list of slots.
     * @param slots the list of Slot instances to display in the view's slot 
     * table.
     */
    public void updateSlotTable(List<? extends Slot> slots) {
        SetupItemsPanel setItemsPanel = view.getSetItemsPanel().getContent();
        
        for (int i = 0; i < slots.size(); i++) {
            String name = (slots.get(i).getSampleItem() == null) 
                ? "[empty]" 
                : slots.get(i).getSampleItem().getName();

            setItemsPanel.getSlotTable().setCell(0, i, i + 1);
            setItemsPanel.getSlotTable().setCell(1, i, name);
        }
    }
}
