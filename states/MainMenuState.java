package states;

import controllers.MainMenuController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.MainMenuView;

public class MainMenuState extends State {
    private MainMenuView view;
    private MainMenuController controller;

    public MainMenuState() {
        view = null;
        controller = null;
    }

    @Override
    protected Controller getController() {
        return controller;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void initialize(VendingMachineModel model) {
        view = new MainMenuView();
        controller = new MainMenuController(model, view);
    }
}
