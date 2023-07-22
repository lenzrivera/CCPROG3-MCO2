package model;

public class VendingMachineModel {
    private VendingMachine<? extends Slot> machine;

    public VendingMachineModel() {
        machine = null;
    }

    public VendingMachine<? extends Slot> getVendingMachine() {
        return machine;
    }

    public void setVendingMachine(VendingMachine<? extends Slot> v) {
        machine = v;
    }
}
