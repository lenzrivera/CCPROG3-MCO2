package controllers;

import controllers.templates.CreateMachineController;
import model.VendingMachine;
import model.VendingMachineModel;
import views.templates.CreateMachineView;

public class CreateSpecialMachineController extends CreateMachineController {
    public CreateSpecialMachineController(VendingMachineModel m) {
        super(m);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected VendingMachine getMachine() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMachine'");
    }

    @Override
    protected CreateMachineView getView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }
}
