package controllers;

import states.CreateRegularMachineState;
import states.CreateSpecialMachineState;
import states.MainMenuState;
import util.Controller;
import views.CreateMachineMenuView;

/**
 * This controller class is responsible for handling user interactions
 * in the create machine menu, allowing them to choose which kind of machine to
 * create.
 */
public class CreateMachineMenuController extends Controller {
    /**
     * The view associated with this controller, particularly 
     * CreateMachineMenuView.
     */
    private CreateMachineMenuView view;

    /**
     * Constructs a new CreateMachineMenuController object. This controller
     * does not rely on the application model, so only the view is needed in. 
     * initialization.
     * @param v the CreateMachineMenuView associated with this controller.
     */
    public CreateMachineMenuController(CreateMachineMenuView v) {
        view = v;

        // Set the back button listener to return to the main menu state.
        view.setBackButtonListener(e -> {
            changeState(new MainMenuState());
        });

        // Set the create regular button listener to transition to the 
        // CreateRegularMachineState.
        view.setCreateRegularButtonListener(e -> {
            changeState(new CreateRegularMachineState());
        });

        // Set the create special button listener to transition to the 
        // CreateSpecialMachineState.
        view.setCreateSpecialButtonListener(e -> {
            changeState(new CreateSpecialMachineState());
        });
    }
}
