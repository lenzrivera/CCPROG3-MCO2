package controllers;

import controllers.templates.TestMaintenanceController;
import model.SpecialVendingMachine;
import model.VendingMachineModel;
import views.TestSpecialMaintenanceView;

public class TestSpecialMaintenanceController extends 
    TestMaintenanceController<TestSpecialMaintenanceView, StockSpecialItemsController>  
{
    private SetupPresetsController presetsController;

    public TestSpecialMaintenanceController(
        VendingMachineModel m, 
        TestSpecialMaintenanceView v
    ) {
        super(m, v);
    }

    @Override
    protected StockSpecialItemsController initStockItemsController() {
        return new StockSpecialItemsController(
            (SpecialVendingMachine) model.getVendingMachine(), 
            view.getStockItemsPanel(), 
            view
        );
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        presetsController = new SetupPresetsController(
            (SpecialVendingMachine) model.getVendingMachine(), 
            view.getSetupPresetsPanel(), 
            view  
        );
    }
}
