package controllers.templates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import util.Controller;
import views.templates.CreateMachineView;

/**
 * This abstract class contains the common functionality needed by the 
 * controllers handling vending machine creation. The types of its view and
 * internal vending machine instance are parametrized to minimize extra code
 * for casting their types.
 */
public abstract class CreateMachineController<
    T extends CreateMachineView<?>, 
    U extends VendingMachine<?>> extends Controller
{
    /**
     * The VendingMachineModel for the whole simulator.
     */
    protected VendingMachineModel model;

    /**
     * The CreateMachineView associated with this controller.
     */
    protected T view;

    /**
     * The vending machine being created by this controller.
     */
    protected U machine;

    /**
     * Constructs a new CreateMachineController object, correspondingly
     * initializing its model, view, and machine to be created.
     * @param m the VendingMachineModel associated with this controller.
     * @param v the specific CreateMachineView associated with this controller.
     */
    public CreateMachineController(VendingMachineModel m, T v) {
        model = m;
        view = v;
        machine = null;

        setConstants();
    }

    /**
     * Sets the constants and values for the view which limit the user's 
     * actions to the required specifications.
     */
    protected void setConstants() {
        view.getBasicInfoPanel()
            .setMinSlotCount(VendingMachine.MIN_SLOT_COUNT);
        view.getBasicInfoPanel()
            .setMinSlotCapacity(Slot.MIN_MAX_CAPACITY);

        view.getStockChangePanel().setDenominations(
            Arrays.stream(DenominationMap.VALID_DENOMINATIONS).boxed().toList());
    }

    /**
     * Updates the stock change panel's denomination table with the provided 
     * DenominationMap.
     * @param denomMap the DenominationMap containing the denominations and quantities.
     */
    protected void updateDenominationTable(DenominationMap denomMap) {
        Map<Double, Integer> rawMap = denomMap.getDenominations();

        ArrayList<Double> denominations = new ArrayList<>(rawMap.keySet());
        ArrayList<Integer> quantities = new ArrayList<>(rawMap.values());

        for (int i = 0; i < rawMap.size(); i++) {
            view.getStockChangePanel()
                .setDenominationCell(i + 1, denominations.get(i));
            view.getStockChangePanel()
                .setQuantityCell(i + 1, quantities.get(i));
        }
    }

    /**
     * Updates the stock items panel's slot table with the provided list of slots.
     * Each row in the table represents a slot with its slot number and the item name 
     * (or [empty] for empty slots).
     * @param slots the list of slots to be displayed in the stock items panel.
     */
    protected void updateSlotTable(List<? extends Slot> slots) {
        for (int i = 0; i < slots.size(); i++) {
            String name = (slots.get(i).getSampleItem() == null) 
                ? "[empty]" 
                : slots.get(i).getSampleItem().getName();

            view.getStockItemsPanel().setSlotNumberCell(i + 1, i + 1);
            view.getStockItemsPanel().setItemNameCell(i + 1, name);
        }
    }
}
