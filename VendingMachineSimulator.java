import model.VendingMachineModel;
import util.State;

public class VendingMachineSimulator {
    private VendingMachineModel model;
    
    private State currentState;
    // private MainWindow mainWindow;
    
    public VendingMachineSimulator() {
        model = new VendingMachineModel();

        currentState = null;
        // mainWindow = new MainWindow();
    }

    public void setCurrentState(State state) {
        currentState = state;

        if (state == null) {
            // mainWindow.close();
            return;
        }

        currentState.initialize(model);
        // mainWindow.setCurrentView(currentState.getView());

        currentState.setChangeListener(nextState -> {
            setCurrentState(nextState);
        });
    }

    public static void main(String[] args) {
        VendingMachineSimulator vms = new VendingMachineSimulator();
        vms.setCurrentState(null);
    }
}