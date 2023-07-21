package controllers;

import java.awt.event.ActionEvent;

// import states.CreateRegularMachineState;
// import states.CreateSpecialMachineState;
import states.MainMenuState;
import util.Controller;
import views.CreateMachineMenuView;

public class CreateMachineMenuController extends Controller {
    private CreateMachineMenuView view;

    public CreateMachineMenuController(CreateMachineMenuView v) {
        view = v;

        view.setBackButtonListener((ActionEvent e) -> {
            changeState(new MainMenuState());
        });

        view.setCreateRegularButtonListener((ActionEvent e) -> {
            // changeState(new CreateRegularMachineState());
        });

        view.setCreateSpecialButtonListener((ActionEvent e) -> {
            // changeState(new CreateSpecialMachineState());
        });
    }
}
