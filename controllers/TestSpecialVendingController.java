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
import views.components.PresetDisplay;
import views.components.SpecialItemDisplay;

import java.io.IOException;
import java.util.List;
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
        SpecialVendingMachine machine =
                (SpecialVendingMachine) model.getVendingMachine();

        /* loop all presets and set actionlistener */

        int presetNo = 1;
        for (Preset presetItem : machine.getPresets()) {
            PresetDisplay presetDisplay;
            double price = 0.0;
            double calories = 0.0;
            try {
                for (Map.Entry<String, Integer> preset : presetItem.getItems().entrySet()) {
                    for (Slot slot : machine.getSlots()) {
                        if (slot.getSampleItem().getName().equalsIgnoreCase(preset.getKey())) {
                            price += slot.getUnitPrice();
                            calories += slot.getSampleItem().getCalories();
                        }
                    }
                }
                presetDisplay = new PresetDisplay(presetItem.getName(), calories, price);
            } catch (NullPointerException e) {
                presetDisplay = new PresetDisplay("[Empty]",-1.0,-1.0);
            }

            // view.setPresetItems(presetDisplay);

            final int tempPresetNo = presetNo;
            PresetDisplay finalPresetDisplay = presetDisplay;
            presetDisplay.setSelectButtonListener(e -> {
                try {
                    if (finalPresetDisplay.getSelectButton().getName()
                            .equalsIgnoreCase("Select")) {

                        finalPresetDisplay.getSelectButton().setText("Deselect");
                        ((SpecialVendingMachine) machine.getPresets()).addSelection(tempPresetNo);
                    } else {
                        finalPresetDisplay.getSelectButton().setText("Select");
                        ((SpecialVendingMachine) model.getVendingMachine()).deselectPreset();
                        view.inputLog("Deselected preset. Selected items has been reset.");
                    }
                } catch (InsufficientStockException ex) {
                    view.inputLog("Insufficient item stock.");
                }
            });
            presetNo++;
        }

        /* loop all slot items and set changelistener */

        int slotNo = 1;
        for (Slot slotItem : machine.getSlots()) {
            SpecialItemDisplay specialItemDisplay;
            try {
                if (slotItem.getSampleItem() == null) {
                    view.addSlot("[Empty]",
                            0,
                            0,
                            0,
                            "imags/outofstock.png",
                            false
                    );
                } else {
                    String itemName = slotItem.getSampleItem().getName();
                    double itemPrice = slotItem.getUnitPrice();
                    double itemCalories = slotItem.getSampleItem().getCalories();
                    int itemStock = slotItem.getStock();
                    String itemImage = slotItem.getSampleItem().getImagePath();

                    view.addSlot(
                            itemName,
                            itemPrice,
                            itemCalories,
                            itemStock,
                            itemImage,
                            true
                    );
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /*
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

            view.setSlotItems(specialItemDisplay);

            final int tempSlotNo = slotNo;
            specialItemDisplay.setSpinnerListener(e -> {
                try {
                    ((SpecialVendingMachine) machine.getSlots()).addSelection(tempSlotNo);
                } catch (InsufficientStockException ex) {
                    view.inputLog("Insufficient item stock.");
                }
            });
            slotNo++;
            */
        }

        view.setExitButtonListener(e -> {
            changeState(new MainMenuState());
        });

        view.setInputCreditButtonListener(e -> {
            machine.getCredit().add(Denomination.toEnum(view.getSelectedDenom()),1);
            view.updateTotalCredit(machine.getCredit().getTotal());
        });

        view.setReturnCreditButtonListener(e -> {
            if (machine.getCredit().getTotal() == 0) {
                return;
            }

            view.displayDenominations("Returned Credit:",
                    machine.getCredit().collect().getQuantityMap()
            );
            view.updateTotalCredit(machine.getCredit().getTotal());
        });

        view.setFinalizeButtonListener(e -> {
            try {
                DispenseResult result = machine.dispenseSelection();
                // for()
                // view.inputLog();
            } catch (MissingChangeException ex) {
                view.inputLog("Machine has insufficient change.");
            } catch (InsufficientCreditException ex) {
                view.inputLog("You have insufficient credit for the product.");
            } catch (InsufficientStockException ex) {
                view.inputLog("Machine has insufficient stock.");
            }
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
