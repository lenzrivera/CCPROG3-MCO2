package states;

import controllers.CreateRegularMachineController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.CreateRegularMachineView;

/**
 * Represents the state where the user can create a regular vending machine.
 */
public class CreateRegularMachineState extends State {
    /**
     * The view associated with this state, particularly 
     * CreateRegularMachineView.
     */
    private CreateRegularMachineView view;

    /**
     * The controller associated with this state, particularly
     * CreateRegularMachineController.
     */
    private CreateRegularMachineController controller;

    /**
     * Constructs a new CreateRegularMachineState object. The view and controller
     * is initially set to null and initialized only when initialize() is 
     * called.
     */
    public CreateRegularMachineState() {
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
        view = new CreateRegularMachineView();
        controller = new CreateRegularMachineController(model, view);
    }
}
