package util;

public abstract class Controller {
    private StateChangeListener stateChangeListener;

    public Controller() {
        stateChangeListener = null;
    }

    protected void changeState(State nextState) {
        assert stateChangeListener != null;
        stateChangeListener.onChange(nextState);
    }

    public void setStateChangeListener(StateChangeListener listener) {
        stateChangeListener = listener;
    }
}
