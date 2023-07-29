package controllers.templates;

import java.util.ArrayList;
import java.util.Map;

import model.Denomination;
import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import states.MainMenuState;
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
     * The controller for the particular stock items section of the creation
     * menu. The StockItemsController varies depending on whether a regular or
     * special machine is being created, hence the need for generics.
     */
    protected StockItemsController<?, ?> stockItemsController;
    
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

        // Can't be initialized immediately as the machine to control does not
        // exist yet at initialization.
        stockItemsController = null;

        setConstants();
        setListeners();
    }

    protected abstract void handleBasicInfoNext();
    
    protected abstract void handleStockItemsNext();

    /**
     * Sets the constants and values for the view which limit the user's 
     * actions to the required specifications.
     */
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

    /**
     * Sets the listeners to the view which would handle user input and
     * translate them into data changes that will be reflected back to the 
     * user.
     */
    protected void setListeners() {
        // Common to both regular and special creation menus:
        
        view.setExitButtonListener(e -> {
            // Exit without saving anything.
            changeState(new MainMenuState());
        });

        /* BasicInfoPanel */

        view.getBasicInfoPanel().setNextButtonListener(e -> {
            handleBasicInfoNext();
        });

        /* StockItemsPanel */

        view.getStockItemsPanel().setNextButtonListener(e -> {
            handleStockItemsNext();
        });

        /* StockChangePanel */

        view.getStockChangePanel().getContent().setAddDenominationListener(e -> {
            double denom = 
                view.getStockChangePanel().getContent().getSelectedDenom();
            int quantity = 
                view.getStockChangePanel().getContent().getSelectedQuantity();

            machine.getMoneyStock().add(Denomination.toEnum(denom), quantity);
            updateDenominationTable(machine.getMoneyStock());
        });

        view.getStockChangePanel().setNextButtonListener(e -> {
            model.setVendingMachine(machine);
            changeState(new MainMenuState());
        });
    }

    /**
     * Updates the stock change panel's denomination table with the provided 
     * DenominationMap.
     * @param denomMap the DenominationMap containing the denominations and quantities.
     */
    protected void updateDenominationTable(DenominationMap denomMap) {
        Map<Denomination, Integer> rawMap = denomMap.getQuantityMap();

        ArrayList<Denomination> denominations = new ArrayList<>(rawMap.keySet());
        ArrayList<Integer> quantities = new ArrayList<>(rawMap.values());

        for (int i = 0; i < rawMap.size(); i++) {
            view.getStockChangePanel()
                .getContent()
                .getDenomTable()
                .setDenominationCell(i + 1, denominations.get(i).getValue());
            view.getStockChangePanel()
                .getContent()
                .getDenomTable()
                .setQuantityCell(i + 1, quantities.get(i));
        }
    }
}
