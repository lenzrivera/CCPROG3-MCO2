package controllers;

import states.CreateRegularMachineState;
import states.CreateSpecialMachineState;
import states.MainMenuState;
import util.Controller;
import views.CreateMachineMenuView;

public class CreateMachineMenuController extends Controller {
    private CreateMachineMenuView view;

    public CreateMachineMenuController(CreateMachineMenuView v) {
        view = v;

        view.setBackButtonListener(e -> {
            changeState(new MainMenuState());
        });

        view.setCreateRegularButtonListener(e -> {
            changeState(new CreateRegularMachineState());
        });

        view.setCreateSpecialButtonListener(e -> {
            changeState(new CreateSpecialMachineState());
        });
    }
}
