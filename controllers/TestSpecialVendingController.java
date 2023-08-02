package controllers;

import model.*;
import model.exceptions.CreditException;
import model.exceptions.SelectedNonStandaloneException;
import model.exceptions.SlotException;
import states.TestMachineMenuState;
import util.Controller;
import views.TestSpecialVendingView;

import java.io.IOException;

/**
 * A controller class for managing the testing of the vending features of 
 * a special vending machine.
 */
public class TestSpecialVendingController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    private VendingMachineModel model;

    /**
     * The view associated with the controller, particularly 
     * TestSpecialVendingView.
     */
    private TestSpecialVendingView view;

    /**
     * Constructs a new TestSpecialVendingController with the provided 
     * VendingMachineModel and TestSpecialVendingView.
     * @param model The VendingMachineModel to be associated with the 
     * controller.
     * @param view The TestSpecialVendingView to be associated with the 
     * controller.
     */
    public TestSpecialVendingController(
        VendingMachineModel model, 
        TestSpecialVendingView view
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
        SpecialVendingMachine machine =
            (SpecialVendingMachine) model.getVendingMachine();

        // Presets initialization

        int presetNo = 1;

        for (Preset preset : machine.getPresets()) {
            try {
                view.addPreset(
                    preset.getName(),
                    machine.getPresetPrice(presetNo),
                    machine.getPresetCalories(presetNo),
                    preset.getImagePath()
                );
            } catch (IOException e) {
                view.showErrorDialog("Cannot load preset image!");
            }

            final int finalPresetNo = presetNo;
            view.getPresetDisplay(presetNo).setSelectButtonListener(e -> {
                // Blank slate on each selection.
                machine.deselectPreset();

                if (!view.getPresetDisplay(finalPresetNo).canBeSelected()) {
                    // Preset is already deselected so there's no need to 
                    // deselect again.
                    view.inputLog(
                        "Deselected preset. Selected items have been reset."
                    );
                } else {
                    machine.selectPreset(finalPresetNo);
                }

                updateItemsView();
                updatePresetsView();
            });

            presetNo++;
        }
        updatePresetsView();

        // Items initialization

        int slotNo = 1;

        for (SpecialSlot slot : machine.getSlots()) {
            try {
                if (slot.getSampleItem() == null) {
                    view.addSlot(
                        "[Empty]",
                        0,
                        0,
                        0,
                        "images/outofstock.png",
                        false
                    );
                } else {
                    view.addSlot(
                        slot.getSampleItem().getName(),
                        slot.getUnitPrice(),
                        slot.getSampleItem().getCalories(),
                        slot.getStock(),
                        slot.getSampleItem().getImagePath(),
                        slot.isStandalone()
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
                
                    view.inputLog("Dispensed " + result.getName() + ".");
                    view.inputLog(
                        "Change: P" + result.getChange().getTotal() + "\n"
                    );

                    if (result.getChange().getTotal() != 0) {
                        view.displayDenominations(
                            "Returned Change:",
                            result.getChange().getQuantityMap()
                        );
                    }

                    updateItemsView();
                    updatePresetsView();
                } catch (CreditException ex) {
                    view.inputLog(ex.getMessage());

                    if (ex.getReturnedCredit().getTotal() != 0) {
                        view.displayDenominations(
                            "Returned Credit:",    
                            ex.getReturnedCredit().getQuantityMap()
                        );   
                    }
                } catch (SelectedNonStandaloneException ex) {
                    view.inputLog(ex.getMessage());
                }

                view.updateTotalCredit(machine.getCredit().getTotal());
            });

            view.getItemDisplay(slotNo).setSpinnerChangeListener(e -> {
                Integer qtySelected = machine.getSelectedSlots().get(slot);
                
                try {
                    // If selection reduced:
                    if (
                        qtySelected != null &&
                        view.getItemDisplay(finalSlotNo)
                            .getSpinnerValue() < qtySelected
                    ) {
                        machine.removeSelection(finalSlotNo);                
                    } else {
                        machine.addSelection(finalSlotNo);
                    }

                    updateItemsView();
                    updatePresetsView();
                    view.updateTotalPayment(machine.getTotalPrice());
                    view.updateTotalCalories(machine.getTotalCalories());
                } catch (SlotException ex) {
                    view.inputLog(ex.getMessage());
                    view.getItemDisplay(finalSlotNo).setSpinnerValue(qtySelected);
                }
            });

            slotNo += 1;   
        }
        updateItemsView();

        // Other buttons

        view.setExitButtonListener(e -> {
            machine.getCredit().collect();
            machine.deselectPreset();
            
            changeState(new TestMachineMenuState());
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

            view.displayDenominations("Returned Credit:",
                machine.getCredit().collect().getQuantityMap()
            );
            view.updateTotalCredit(machine.getCredit().getTotal());
        });

        view.setFinalizeButtonListener(e -> {
            try {
                DispenseResult result = machine.dispenseSelection();
            
                // Prepartion simulation

                view.inputLog("Preparing order...");

                for (String msg : result.getProcessMessages()) {
                    view.inputLog("    " + msg);
                }
            
                view.inputLog("Done!\n");

                // Dispensing

                view.inputLog("Dispensed " + result.getName() + ".");
                view.inputLog(
                    "Change: P" + result.getChange().getTotal() + "\n"
                );

                if (result.getChange().getTotal() != 0) {
                    view.displayDenominations(
                        "Returned Change:",
                        result.getChange().getQuantityMap()
                    );
                }

                // Cleanup

                updateItemsView();
                updatePresetsView();

                view.updateTotalCredit(machine.getCredit().getTotal());
                view.updateTotalCalories(machine.getTotalCalories());
                view.updateTotalPayment(machine.getTotalPrice());
            } catch (CreditException ex) {
                view.inputLog(ex.getMessage());

                if (ex.getReturnedCredit().getTotal() != 0) {
                    view.displayDenominations(
                        "Returned Credit:",    
                        ex.getReturnedCredit().getQuantityMap()
                    );   

                    view.updateTotalCredit(machine.getCredit().getTotal());
                }
            }
        });
    }

    /* */

    /**
     * Updates the view for the items in the vending machine 
     * user interface.
     */
    private void updateItemsView() {
        SpecialVendingMachine machine =
            (SpecialVendingMachine) model.getVendingMachine();

        int slotNo = 1;
        
        for (SpecialSlot slot : machine.getSlots()) {
            view.getItemDisplay(slotNo)
                .setStockValue(slot.getStock());

            view.getItemDisplay(slotNo)
                .setSelectEnable(
                    slot.getStock() != 0 &&
                    (machine.getSelectedPreset() != null || 
                     slot.isStandalone())
                );
            view.getItemDisplay(slotNo)
                .setSpinnerVisible(machine.getSelectedPreset() != null);

            if (machine.getSelectedSlots().containsKey(slot)) {
                int selQty = machine.getSelectedSlots().get(slot);

                view.getItemDisplay(slotNo).setSpinnerValue(selQty);
                view.getItemDisplay(slotNo).setSpinnerMaximum(
                    (slot.isBase() && slot.getStock() > 0) ? 1 : slot.getStock()
                );
            }

            slotNo++;
        }

        view.updateTotalCalories(machine.getTotalCalories());
        view.updateTotalPayment(machine.getTotalPrice());
    }


    /**
     * Updates the view for the presets in the vending machine 
     * user interface.
     */
    private void updatePresetsView() {
        SpecialVendingMachine machine =
            (SpecialVendingMachine) model.getVendingMachine();

        int presetNo = 1;

        for (Preset preset : machine.getPresets()) {
            view.getPresetDisplay(presetNo)
                .setSelectEnable(
                    machine.hasEnoughStockFor(presetNo) &&
                    machine.getSelectedPreset() == null || 
                    machine.getSelectedPreset() == preset
                );

            view.getPresetDisplay(presetNo)
                .setSelectState(
                    machine.getSelectedPreset() == preset
                );

            view.getPresetDisplay(presetNo)
                .setHighlightShow(
                    machine.getSelectedPreset() == preset &&
                    machine.hasDeviatedFrom(presetNo)
                );

            presetNo++;
        }

        view.setFinalizeEnabled(machine.getSelectedPreset() != null);
    }
}
