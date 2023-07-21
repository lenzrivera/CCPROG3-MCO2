package controllers;

import java.awt.event.ActionEvent;

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

        view.setCreateButtonListener((ActionEvent e) -> {
            changeState(new CreateMachineMenuState());
        });

        view.setExitButtonListener((ActionEvent e) -> {
            changeState(null);
        });

        view.setTestButtonListener((ActionEvent e) -> {
            changeState(new TestMachineMenuState());
        });
    }
}
