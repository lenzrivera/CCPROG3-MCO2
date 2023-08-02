package controllers;

import model.*;
import model.exceptions.MissingChangeException;
import model.exceptions.InsufficientCreditException;
import model.exceptions.InsufficientStockException;
import states.MainMenuState;
import states.TestMachineMenuState;
import util.Controller;
import views.TestSpecialVendingView;
import views.components.ItemDisplay;
import views.components.SpecialItemDisplay;

import java.util.Map;
import java.util.Set;

public class TestSpecialVendingController extends Controller {
    private VendingMachineModel model;

    private TestSpecialVendingView view;
    public TestSpecialVendingController(VendingMachineModel model, TestSpecialVendingView view) {
        this.model = model;
        this.view = view;
        
        view.setDenominations(Denomination.getDoubleValues());
        setListeners();
    }

    protected void setListeners() {
        int slotNo = 1;
        SpecialVendingMachine machine = (SpecialVendingMachine) model.getVendingMachine();
        for (Preset presetItem : machine.getPresets()) {
            ItemDisplay itemDisplay;
            try {
                for (Map.Entry<String,Integer> x : presetItem.getItems().entrySet()) {
                    double calories = 0.0;
                    for (Slot l : model.vending)
                }
                itemDisplay = new ItemDisplay(presetItem.getName());
            }
        }
        for (Slot slotItem : machine.getSlots()) {
            SpecialItemDisplay specialItemDisplay;
            try {
                String itemName = slotItem.getSampleItem().getName();
                double itemPrice = slotItem.getUnitPrice();
                double itemCalories = slotItem.getSampleItem().getCalories();
                int itemStock = slotItem.getStock();
                specialItemDisplay = new SpecialItemDisplay(itemName,
                        itemPrice,
                        itemCalories,
                        itemStock);
            } catch (NullPointerException e) {
                specialItemDisplay = new SpecialItemDisplay("[Empty]",
                        -1.0,
                        -1.0,
                        -1);
                specialItemDisplay.getSpinner().setEnabled(false);
            }
            view.setPresetItems(specialItemDisplay);
            final int tempSlotNo = slotNo;
            specialItemDisplay.setSpinnerListener(e -> {
                try {

                } catch (MissingChangeException ex) {
                    view.inputLog("Machine has insufficient change.");
                } catch (InsufficientCreditException ex) {
                    view.inputLog("You have insufficient credit for the product.");
                } catch (InsufficientStockException ex) {
                    view.inputLog("Machine has insufficient stock.");
                }
            });
            slotNo++;
        }

        view.setExitButtonListener(e -> {
            changeState(new MainMenuState());
        });

        view.setBackButtonListener(e -> {
            changeState(new TestMachineMenuState());
        });

        view.setInputCreditButtonListener(e -> {
            machine.getCredit().add(Denomination.toEnum(view.getSelectedDenom()),1);
            view.updateTotalCredit(machine.getCredit().getTotal());
        });

        view.setReturnCreditButtonListener(e -> {
            view.displayDenominations(machine.getCredit().collect().getQuantityMap());
            view.updateTotalCredit(machine.getCredit().getTotal());
        });

        view.setFinalizeButtonListener(e -> {

        });
    }

    private double getItemCalories(int slotNo) {
        return model.getVendingMachine()
                .getSlot(slotNo).getSampleItem().getCalories();
    }

    private double getItemPrice(int slotNo) {
        return model.getVendingMachine().getSlot(slotNo).getUnitPrice();
    }
}
