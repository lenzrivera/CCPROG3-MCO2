package util;

import model.VendingMachineModel;

/**
 * The State abstract class represents a visual and functional state of the
 * application. Each state associates a view with a controller, both using
 * a uniform model to perform their tasks.
 */
public abstract class State {
    /**
     * Retrieves the controller associated with this state.
     * @return the Controller instance associated with this state
     */
    protected abstract Controller getController();

     /**
     * Retrieves the view associated with this state.
     * @return the View instance associated with this state
     */   
    public abstract View getView();

    /**
     * Passes the main model to the state and initializes its view and 
     * controller using it. Relegating view and controller initialization here
     * allows States to be passed around without initializing them immediately.
     * @param model the VendingMachineModel to be associated with this state
     */
    public abstract void initialize(VendingMachineModel model);

    /**
     * Sets the StateChangeListener for this state's controller.
     * This listener is called when there are state transitions or changes,
     * prompting the main state-change mechanism to perform the state change.
     * @param listener the StateChangeListener to be set for this state's 
     * controller
     */
    public void setChangeListener(StateChangeListener listener) {
        getController().setStateChangeListener(listener);
    }
}
