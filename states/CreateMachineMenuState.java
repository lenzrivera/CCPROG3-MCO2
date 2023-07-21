package states;

import controllers.CreateMachineMenuController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.CreateMachineMenuView;

public class CreateMachineMenuState extends State {
    private CreateMachineMenuView view;
    private CreateMachineMenuController controller;
    
    public CreateMachineMenuState() {
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
        view = new CreateMachineMenuView();
        controller = new CreateMachineMenuController(view);
    }   
}
