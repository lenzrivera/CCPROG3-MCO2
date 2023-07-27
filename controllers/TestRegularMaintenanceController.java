package controllers;

import controllers.templates.TestMaintenanceController;
import model.RegularVendingMachine;
import model.VendingMachineModel;
import views.TestRegularMaintenanceView;

public class TestRegularMaintenanceController extends 
    TestMaintenanceController<TestRegularMaintenanceView, StockRegularItemsController> 
{
    public TestRegularMaintenanceController(
        VendingMachineModel m, 
        TestRegularMaintenanceView v
    ) {
        super(m, v);
    }

    @Override
    protected StockRegularItemsController initStockItemsController() {
        return new StockRegularItemsController(
            (RegularVendingMachine) model.getVendingMachine(), 
            view.getStockItemsPanel(), 
            view
        );
    }    
}
