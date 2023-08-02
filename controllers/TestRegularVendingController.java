package controllers;

import model.*;
import model.exceptions.CreditException;
import states.MainMenuState;
import util.Controller;
import views.TestRegularVendingView;

import java.io.IOException;

/**
 * A controller class for managing the testing of the vending features of 
 * a regular vending machine.
 */
public class TestRegularVendingController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    private VendingMachineModel model;

    /**
     * The view associated with the controller, particularly 
     * TestRegularVendingView.
     */
    private TestRegularVendingView view;

    /**
     * Constructs a new TestRegularVendingController with the provided 
     * VendingMachineModel and TestRegularVendingView.
     * @param model The VendingMachineModel to be associated with the 
     * controller.
     * @param view The TestRegularVendingView to be associated with the 
     * controller.
     */
    public TestRegularVendingController(
        VendingMachineModel model, 
        TestRegularVendingView view
    ) {
        this.model = model;
        this.view = view;

        view.setHeading(model.getVendingMachine().getName());
        view.setDenominations(Denomination.getDoubleValues());
        setListeners();
    }

    /**
     * Sets up the event listeners for the view.
     */
    protected void setListeners() {
        RegularVendingMachine machine = 
            (RegularVendingMachine) model.getVendingMachine();

        int slotNo = 1;
        for (Slot slotItem : machine.getSlots()) {
            try {
                if (slotItem.getSampleItem() == null) {
                    view.addSlot(
                        "[Empty]",
                        0,
                        0,
                        0,
                        "images/outofstock.png",
                        false
                    );
                } else {
                    String itemName = slotItem.getSampleItem().getName();
                    double itemPrice = slotItem.getUnitPrice();
                    double itemCalories = slotItem.getSampleItem().getCalories();
                    String itemImage = slotItem.getSampleItem().getImagePath();
                    int itemStock = slotItem.getStock();

                    view.addSlot(
                        itemName,
                        itemPrice,
                        itemCalories,
                        itemStock,
                        itemImage,
                        itemStock != 0
                    );     
                }
            } catch (IOException e) {
                view.showErrorDialog("Cannot load item image!");
            }

            final int finalSlotNo = slotNo;
            view.getItemDisplay(slotNo).setSelectButtonListener(e -> {
                try {
                    machine.addSelection(finalSlotNo);
                    DispenseResult result = machine.dispenseSelection();
                    
                    view.inputLog(result.getProcessMessages().get(0));
                    view.inputLog("Change: P" + result.getChange().getTotal());

                    if (result.getChange().getTotal() != 0) {
                        view.displayDenominations(
                            "Returned Change:",
                            result.getChange().getQuantityMap()
                        );
                    }
                } catch (CreditException ex) {
                    view.inputLog(ex.getMessage());

                    if (ex.getReturnedCredit().getTotal() != 0) {
                        view.displayDenominations(
                            "Returned Credit:",    
                            ex.getReturnedCredit().getQuantityMap()
                        );   
                    }
                }

                view.getItemDisplay(finalSlotNo)
                    .setStockValue(slotItem.getStock());

                if (slotItem.getStock() == 0) {
                    view.getItemDisplay(finalSlotNo).setButtonEnabled(false);
                }

                view.updateTotalCredit(machine.getCredit().getTotal());
            });

            slotNo++;
        }

        view.setExitButtonListener(e -> {
            changeState(new MainMenuState());
        });

        view.setInputCreditButtonListener(e -> {
            machine.getCredit().add(
                Denomination.toEnum(view.getSelectedDenom()),
                1
            );
            view.updateTotalCredit(machine.getCredit().getTotal());
        });

        view.setReturnCreditButtonListener(e -> {
            if (machine.getCredit().getTotal() == 0) {
                return;
            } 

            view.displayDenominations(
                "Returned Credit:",    
                machine.getCredit().collect().getQuantityMap()
            );
            view.updateTotalCredit(machine.getCredit().getTotal());
        });
    }
}
