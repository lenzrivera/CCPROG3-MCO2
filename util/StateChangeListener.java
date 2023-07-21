package util;

import java.util.EventListener;

public interface StateChangeListener extends EventListener {
    public void onChange(State state);
}
