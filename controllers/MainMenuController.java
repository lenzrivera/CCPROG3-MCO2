package controllers;

import model.VendingMachineModel;
import states.CreateMachineMenuState;
import states.TestMachineMenuState;
import util.Controller;
import views.MainMenuView;

/**
 * This controller class is responsible for handling user interactions in
 * the main menu, allowing them to either create or test a vending machine.
 */
public class MainMenuController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    private VendingMachineModel model;

    /**
     * The view associated with this controller, particularly 
     * MainMenuView.
     */
    private MainMenuView view;
    
    /**
     * Constructs a MainMenuController object with its associated model and 
     * view.
     * @param m the VendingMachineModel associated with this controller
     * @param v the MainMenuView associated with this controller
     */
    public MainMenuController(VendingMachineModel m, MainMenuView v) {
        model = m;
        view = v;

        if (model.getVendingMachine() == null) {
            view.setTestButtonEnabled(false);
            view.setTestButtonToolTip("No vending machine to test yet.");
        }

        view.setCreateButtonListener(e -> {
            changeState(new CreateMachineMenuState());
        });

        view.setExitButtonListener(e -> {
            changeState(null);
        });

        view.setTestButtonListener(e -> {
            changeState(new TestMachineMenuState());
        });
    }
}
