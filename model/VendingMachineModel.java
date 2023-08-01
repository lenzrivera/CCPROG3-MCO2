package model;

/**
 * This class represents the model of the program, particularly containing
 * the vending machine to be tested. It allows for the setting and retrieval
 * of such machine. 
 */
public class VendingMachineModel {
    /**
     * The vending machine created, if any.
     */
    private VendingMachine<? extends Slot> machine;

    /**
     * Constructs a new VendingMachineModel instance with no vending 
     * machine assigned.
     */
    public VendingMachineModel() {
        machine = null;
    }

    /* */

    /**
     * Returns the current vending machine instance assigned to the model.
     * @return the current vending machine instance.
     */
    public VendingMachine<? extends Slot> getVendingMachine() {
        return machine;
    }

    /**
     * Sets the vending machine to be used in the model.
     * @param v The vending machine instance to be set.
     */
    public void setVendingMachine(VendingMachine<? extends Slot> v) {
        machine = v;
    }
}