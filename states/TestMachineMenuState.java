package states;

import controllers.TestMachineMenuController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.TestMachineMenuView;

/**
 * Represents the state where the user can test either the vending or 
 * maintenance features of a vending machine.
 */
public class TestMachineMenuState extends State {
    /**
     * The view associated with this state, particularly TestMachineMenuView.
     */
    private TestMachineMenuView view;

    /**
     * The controller associated with this state, particularly
     * TestMachineMenuController.
     */
    private TestMachineMenuController controller;

    /**
     * Constructs a new TestMachineMenuState object. The view and controller
     * is initially set to null and initialized only when initialize() is 
     * called.
     */
    public TestMachineMenuState() {
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
        assert model.getVendingMachine() != null;
        
        view = new TestMachineMenuView();
        controller = new TestMachineMenuController(model, view);
    }
}
