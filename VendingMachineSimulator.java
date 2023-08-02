import model.VendingMachineModel;
import states.MainMenuState;
import util.MainWindow;
import util.State;

/**
 * This class is the main or driver class of the vending machine simulator. 
 * It stores the application model and handles state switching. 
 */
public class VendingMachineSimulator {
    /**
     * The model representing the current vending machine.
     */
    private VendingMachineModel model;
    
    /**
     * The current state of the application.
     */
    private State currentState;
    
    /**
     * The main window of the application.
     */
    private MainWindow mainWindow;
    
    /**
     * Constructs a new VendingMachineSimulator object. The current state
     * is null by default.
     */
    public VendingMachineSimulator() {
        model = new VendingMachineModel();
        currentState = null;
        mainWindow = new MainWindow();
    }

    /**
     * Sets the current state of the application. It initializes the new state 
     * and updates the main window accordingly. If the state is set to null, 
     * it closes the main window and terminates the application.
     * @param state the new State to be set as the current state of the 
     * simulator.
     */
    public void setCurrentState(State state) {
        currentState = state;

        if (state == null) {
            mainWindow.close();
            return;
        }

        currentState.initialize(model);
        mainWindow.setCurrentView(currentState.getView());

        currentState.setChangeListener(nextState -> {
            setCurrentState(nextState);
        });
    }

    /**
     * The entry point for the application.
     * @param args the command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        VendingMachineSimulator vms = new VendingMachineSimulator();
        vms.setCurrentState(new MainMenuState());
    }
}
