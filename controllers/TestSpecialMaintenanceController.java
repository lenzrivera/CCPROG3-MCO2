package controllers;

import model.VendingMachineModel;
import util.Controller;
import views.TestSpecialMaintenanceView;

public class TestSpecialMaintenanceController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    private VendingMachineModel model;

    /**
     * The view associated with this controller, particularly 
     * TestRegularMaintenanceView.
     */
    private TestSpecialMaintenanceView view;

    public TestSpecialMaintenanceController(
        VendingMachineModel m, 
        TestSpecialMaintenanceView v
    ) {
        model = m;
        view = v;
    }
}
