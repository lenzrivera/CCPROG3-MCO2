package util;

import model.VendingMachineModel;

public abstract class State {
    protected abstract Controller getController();
    public abstract View getView();

    public abstract void initialize(VendingMachineModel model);

    public void setChangeListener(StateChangeListener listener) {
        getController().setStateChangeListener(listener);
    }
}
