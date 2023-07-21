package states;

import controllers.TestMachineMenuController;
import model.VendingMachineModel;
import util.Controller;
import util.State;
import util.View;
import views.TestMachineMenuView;

public class TestMachineMenuState extends State {
    private TestMachineMenuView view;
    private TestMachineMenuController controller;

    public TestMachineMenuState() {
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
        assert model.getVendingMachine() != null;
        
        view = new TestMachineMenuView();
        controller = new TestMachineMenuController(model, view);
    }
}
