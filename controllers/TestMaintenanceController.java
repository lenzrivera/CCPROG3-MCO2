package controllers;

import controllers.templates.StockItemsController;
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

    public TestMaintenanceController(VendingMachineModel model, T view) {
        this.model = model;
        this.view = view;

        setListeners();
    }

    private void setListeners() {
        view.setExitButtonListener(e -> {
            changeState(new MainMenuState());
        });

        // TODO: bind listeners
        view.getSummaryViewPanel();

        // TODO: bind listeners
        // view.getManageMoneyPanel().

        view.getManageMoneyPanel();
    }
}
