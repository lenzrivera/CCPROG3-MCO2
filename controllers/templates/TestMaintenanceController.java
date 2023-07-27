package controllers.templates;

import model.VendingMachineModel;
import states.MainMenuState;
import util.Controller;
import views.templates.TestMaintenanceView;

public abstract class TestMaintenanceController<
    T extends TestMaintenanceView<?>,
    U extends StockItemsController<?, ?>> extends Controller
{
    /**
     * The VendingMachineModel for the whole simulator.
     */
    protected VendingMachineModel model;

    protected T view;

    protected U stockItemsController;

    public TestMaintenanceController(VendingMachineModel model, T view) {
        this.model = model;
        this.view = view;

        setListeners();
        initStockItemsController();
    }

    protected abstract U initStockItemsController();

    protected void setListeners() {
        view.setExitButtonListener(e -> {
            changeState(new MainMenuState());
        });

        stockItemsController = initStockItemsController();

        // TODO: bind listeners
        view.getSummaryViewPanel();

        // TODO: bind listeners
        // view.getManageMoneyPanel().

        view.getManageMoneyPanel();
    }
}
