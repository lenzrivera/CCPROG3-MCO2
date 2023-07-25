package util;

import java.util.EventListener;

/**
 * This interface represents a listener to be invoked when a State calls for
 * for a state change.
 */
public interface StateChangeListener extends EventListener {
    /**
     * This method is called when a state change event occurs.
     * @param state the new State that the program will transition to
     */
    public void onChange(State state);
}
