package controllers;

import model.VendingMachineModel;
import states.CreateMachineMenuState;
import states.TestMachineMenuState;
import util.Controller;
import views.MainMenuView;

public class MainMenuController extends Controller {
    private VendingMachineModel model;
    private MainMenuView view;
    
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
