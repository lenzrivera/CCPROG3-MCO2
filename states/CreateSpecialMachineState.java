package states;

import controllers.CreateSpecialMachineController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.CreateSpecialMachineView;

/**
 * Represents the state where the user can create a special vending machine.
 */
public class CreateSpecialMachineState extends State {
    /**
     * The view associated with this state, particularly 
     * CreateSpecialMachineView.
     */
    private CreateSpecialMachineView view;

    /**
     * The controller associated with this state, particularly
     * CreateSpecialMachineController.
     */
    private CreateSpecialMachineController controller;

    /**
     * Constructs a new CreateSpecialMachineState object. The view and controller
     * is initially set to null and initialized only when initialize() is 
     * called.
     */
    public CreateSpecialMachineState() {
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
        view = new CreateSpecialMachineView();
        controller = new CreateSpecialMachineController(model, view);
    }
}
