package controllers;

import model.RegularVendingMachine;
import model.VendingMachineModel;
import states.MainMenuState;
// import states.TestRegularVendingState;
// import states.TestRegularMaintenanceState;
import util.Controller;
import views.TestMachineMenuView;

/**
 * This controller class handles user interaction in the test machine menu,
 * allowing the user to test either the vending or maintenance features of a 
 * vending machine.
 */
public class TestMachineMenuController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    private VendingMachineModel model;

    /**
     * The view associated with this controller, particularly 
     * TestMachineMenuView.
     */
    private TestMachineMenuView view; 

    /**
     * Constructs a TestMachineMenuController object with its associated model 
     * and view.
     * @param m the VendingMachineModel associated with this controller
     * @param v the TestMachineMenuView associated with this controller
     */
    public TestMachineMenuController(VendingMachineModel m, TestMachineMenuView v) {
        model = m;
        view = v;

        view.setBackButtonListener(e -> {
            changeState(new MainMenuState());
        });

        view.setTestVendingButtonListener(e -> {
            if (model.getVendingMachine() instanceof RegularVendingMachine) {
                // changeState(new TestRegularVendingState());
            } else {
                // changeState(new TestSpecialVendingState());
            }
        });

        view.setTestMaintenanceButtonListener(e -> {
            if (model.getVendingMachine() instanceof RegularVendingMachine) {
                // changeState(new TestRegularMaintenanceState());
            } else {
                // changeState(new TestSpecialMaintenanceState());
            } 
        });
    }
}
