package controllers;

import model.Denomination;
import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import states.MainMenuState;
import util.Controller;
import views.TestMaintenanceView;
import views.components.ManageMoneyPanel;

public class TestMaintenanceController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    protected VendingMachineModel model;

    protected TestMaintenanceView view;

    public TestMaintenanceController(
        VendingMachineModel model, 
        TestMaintenanceView view
    ) {
        this.model = model;
        this.view = view;

        setListeners();
    }

    private void setListeners() {
        VendingMachine<? extends Slot> machine = model.getVendingMachine();

        view.setExitButtonListener(e -> {
            changeState(new MainMenuState());
        });

        // TODO: bind listeners

        view.getStockItemsPanel().setItemAddListener(e -> {

        });

        view.getStockItemsPanel().setItemRemoveListener(e -> {

        });

        // TODO: bind listeners

        view.getManageMoneyPanel().setCollectListener(e -> {
            ManageMoneyPanel panel = view.getManageMoneyPanel();

            double denom = panel.getSelectedDenom();
            int quantity = panel.getSelectedQuantity();
            
            machine.getMoneyStock().remove(Denomination.toEnum(denom), quantity);
            updateDenominationTable(machine.getMoneyStock()); 
        });

        view.getManageMoneyPanel().setCollectAllListener(e -> {
            machine.getMoneyStock().collect();
            updateDenominationTable(machine.getMoneyStock()); 
        });

        view.getManageMoneyPanel().setStockListener(e -> {
            ManageMoneyPanel panel = view.getManageMoneyPanel();

            double denom = panel.getSelectedDenom();
            int quantity = panel.getSelectedQuantity();

            machine.getMoneyStock().add(Denomination.toEnum(denom), quantity);
            updateDenominationTable(machine.getMoneyStock());
        });
    }

    /* */

    private void updateDenominationTable(DenominationMap denomMap) {
        int i = 0;

        for (var entry : denomMap.getQuantityMap().entrySet()) {
            view.getManageMoneyPanel()
                .getDenomTable()
                .setCol0(i, entry.getKey().getValue());
            view.getManageMoneyPanel()
                .getDenomTable()
                .setCol1(i, entry.getValue());
            
            i += 1;
        }
    }
}
