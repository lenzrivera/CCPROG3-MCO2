package states;

import controllers.TestSpecialVendingController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.TestSpecialVendingView;

/**
 * Represents the state where the user can test the vending features of
 * a special vending machine.
 */
public class TestSpecialVendingState extends State {
    /**
     * The view associated with this state, particularly 
     * TestSpecialVendingView.
     */
    private TestSpecialVendingView view;

    /**
     * The controller associated with this state, particularly 
     * TestSpecialVendingController.
     */
    private TestSpecialVendingController controller;

    /**
     * Constructs a new TestSpecialVendingState object. The view and controller
     * is initially set to null and initialized only when initialize() is 
     * called.
     */
    public TestSpecialVendingState() {
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
        view = new TestSpecialVendingView();
        controller = new TestSpecialVendingController(model, view);
    }
}
