package states;

import controllers.TestMaintenanceController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.TestMaintenanceView;

/**
 * Represents the state where the user can test the maintenance features of
 * a regular or special vending machine.
 */
public class TestMaintenanceState extends State {
    /**
     * The view associated with this state, particularly TestMaintenanceView.
     */
    private TestMaintenanceView view;

    /**
     * The controller associated with this state, particularly 
     * TestMaintenanceController.
     */
    private TestMaintenanceController controller;

    /**
     * Constructs a new TestMaintenanceState object. The view and controller
     * is initially set to null and initialized only when initialize() is 
     * called.
     */
    public TestMaintenanceState() {
        view = null;
        controller = null;
    }
    
    /**
     * Retrieves the controller associated with this state.
     * @return the controller associated with this state.
     */
    @Override
    protected Controller getController() {
        return controller;
    }

    /**
     * Retrieves the view associated with this state.
     * @return the view associated with this state.
     */
    @Override
    public View getView() {
        return view;
    }

    /**
     * Passes the main model to the state and initializes its view and 
     * controller using it. Relegating view and controller initialization here
     * allows States to be passed around without initializing them immediately.
     * @param model the VendingMachineModel to be associated with this state
     */
    @Override
    public void initialize(VendingMachineModel model) {
        view = new TestMaintenanceView();
        controller = new TestMaintenanceController(model, view);
    }
}
