package util;

/**
 * This abstract class represents an MVC Controller that mediates between the 
 * model and view. It exposes some utility methods which allows for state 
 * switching between different sets of controllers and views. 
 */
public abstract class Controller {
    /**
     * The listener to invoke when a state change should occur. This should
     * always be set by the main state-switching loop.
     */
    private StateChangeListener stateChangeListener;

    /**
     * This constructor initializes the basic parts of a Controller.
     */
    public Controller() {
        stateChangeListener = null;
    }

    /**
     * Invokes the state change listener assigned by the state-switching
     * mechanism, prompting a state change from the main loop.
     * @param nextState the State object representing the next state
     */
    protected void changeState(State nextState) {
        assert stateChangeListener != null;
        stateChangeListener.onChange(nextState);
    }

    /**
     * Sets the state change listener which runs when a state change
     * takes place.
     * @param listener the StateChangeListener to call on state change
     */
    public void setStateChangeListener(StateChangeListener listener) {
        stateChangeListener = listener;
    }
}
