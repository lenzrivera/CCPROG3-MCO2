package controllers;

// import model.RegularVendingMachine;
import model.VendingMachineModel;
import states.MainMenuState;
// import states.TestRegularVendingState;
// import states.TestRegularMaintenanceState;
import util.Controller;
import views.TestMachineMenuView;

public class TestMachineMenuController extends Controller {
    private VendingMachineModel model;
    private TestMachineMenuView view; 

    public TestMachineMenuController(VendingMachineModel m, TestMachineMenuView v) {
        model = m;
        view = v;

        view.setBackButtonListener(e -> {
            changeState(new MainMenuState());
        });

        view.setTestVendingButtonListener(e -> {
            // if (model.getVendingMachine() instanceof RegularVendingMachine) {
            //     changeState(new TestRegularVendingState());
            // } else {
            //     changeState(new TestSpecialVendingState());
            // }
    });

        view.setTestMaintenanceButtonListener(e -> {
            // if (model.getVendingMachine() instanceof RegularVendingMachine) {
            //     changeState(new TestRegularMaintenanceState());
            // } else {
            //     changeState(new TestSpecialMaintenanceState());
            // } 
        });
    }
}
