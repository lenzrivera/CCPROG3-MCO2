package controllers;

import model.VendingMachineModel;
import states.MainMenuState;
import util.Controller;
import views.TestMaintenanceView;

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
        view.setExitButtonListener(e -> {
            changeState(new MainMenuState());
        });

        // TODO: bind listeners

        // view.getStockItemsPanel().setItemAddListener(e -> {

        // });

        // view.getStockItemsPanel().setItemRemoveListener(e -> {

        // });

        // TODO: bind listeners

        view.getManageMoneyPanel().setCollectListener(e -> {

        });

        view.getManageMoneyPanel().setCollectAllListener(e -> {

        });

        view.getManageMoneyPanel().setStockListener(e -> {

        });
    }
}
