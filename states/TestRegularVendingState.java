package states;

import controllers.TestRegularVendingController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.TestRegularVendingView;

/**
 * Represents the state where the user can test the vending features of
 * a regular vending machine.
 */
public class TestRegularVendingState extends State {
    /**
     * The view associated with this state, particularly 
     * TestRegularVendingView.
     */
    private TestRegularVendingView view;

    /**
     * The controller associated with this state, particularly 
     * TestRegularVendingController.
     */
    private TestRegularVendingController controller;

    /**
     * Constructs a new TestRegularVendingState object. The view and controller
     * is initially set to null and initialized only when initialize() is 
     * called.
     */
    public TestRegularVendingState() {
        view = null;
        controller = null;
    }

    @Override
    protected Controller getController() {
        return controller;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void initialize(VendingMachineModel model) {
        view = new TestRegularVendingView();
        controller = new TestRegularVendingController(model, view);
    }
}
