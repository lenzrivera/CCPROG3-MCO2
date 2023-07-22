package states;

import controllers.CreateRegularMachineController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.CreateRegularMachineView;

public class CreateRegularMachineState extends State {
    private CreateRegularMachineView view;
    private CreateRegularMachineController controller;

    public CreateRegularMachineState() {
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
        view = new CreateRegularMachineView();
        controller = new CreateRegularMachineController(model, view);
    }
}
