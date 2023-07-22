package states;

import controllers.CreateSpecialMachineController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.CreateSpecialMachineView;

public class CreateSpecialMachineState extends State {
    private CreateSpecialMachineView view;
    private CreateSpecialMachineController controller;

    public CreateSpecialMachineState() {
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
        view = new CreateSpecialMachineView();
        controller = new CreateSpecialMachineController(model, view);
    }
}
