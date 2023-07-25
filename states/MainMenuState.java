package states;

import controllers.MainMenuController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.MainMenuView;

/**
 * Represents the state where the user can choose to either create or test a
 * vending machine.
 */
public class MainMenuState extends State {
    /**
     * The view associated with this state, particularly MainMenuView.
     */
    private MainMenuView view;

    /**
     * The controller associated with this state, particularly 
     * MainMenuController.
     */
    private MainMenuController controller;

    /**
     * Constructs a new MainMenuState object. The view and controller
     * is initially set to null and initialized only when initialize() is 
     * called.
     */
    public MainMenuState() {
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
        view = new MainMenuView();
        controller = new MainMenuController(model, view);
    }
}
