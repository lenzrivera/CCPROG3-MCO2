package controllers;

import model.VendingMachineModel;
import util.Controller;
import views.TestRegularMaintenanceView;

public class TestRegularMaintenanceController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    private VendingMachineModel model;

    /**
     * The view associated with this controller, particularly 
     * TestRegularMaintenanceView.
     */
    private TestRegularMaintenanceView view;

    public TestRegularMaintenanceController(
        VendingMachineModel m, 
        TestRegularMaintenanceView v
    ) {
        model = m;
        view = v;
    }
}
