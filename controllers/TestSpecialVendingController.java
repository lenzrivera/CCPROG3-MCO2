package controllers;

import model.*;
import model.exceptions.MissingChangeException;
import model.exceptions.CreditException;
import model.exceptions.InsufficientCreditException;
import model.exceptions.InsufficientStockException;
import states.MainMenuState;
import states.TestMachineMenuState;
import util.Controller;
import views.TestSpecialVendingView;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestSpecialVendingController extends Controller {
    private VendingMachineModel model;

    private TestSpecialVendingView view;

    public TestSpecialVendingController(
        VendingMachineModel model, 
        TestSpecialVendingView view
    ) {
        this.model = model;
        this.view = view;

        view.setDenominations(Denomination.getDoubleValues());
        setListeners();
    }

    protected void setListeners() {
        SpecialVendingMachine machine =
            (SpecialVendingMachine) model.getVendingMachine();

        // Presets initialization

        int presetNo = 1;

        for (Preset preset : machine.getPresets()) {
            view.addPreset(
                preset.getName(),
                machine.getPresetPrice(presetNo),
                machine.getPresetCalories(presetNo)
            );

            view.getPresetDisplay(presetNo).setSelectButtonListener(e -> {
                // Blank slate on each selection.
                machine.deselectPreset();

                if (!view.getPresetDisplay(presetNo).canBeSelected()) {
                    // Preset is already deselected so there's no need to 
                    // deselect again.
                    
                    updateItemsView();
                    view.inputLog(
                        "Deselected preset. Selected items have been reset."
                    );
                    view.setFinalizeEnabled(false);
                } else {
                    machine.selectPreset(presetNo);
                    
                    updateItemsView();
                    view.setFinalizeEnabled(true);
                }
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
                        false,
                        "images/outofstock.png"
                    );
                } else {
                    view.addSlot(
                        slot.getSampleItem().getName(),
                        slot.getUnitPrice(),
                        slot.getSampleItem().getCalories(),
                        slot.getStock(),
                        slot.isStandalone(),
                        slot.getSampleItem().getImagePath()
                    );     
                }
            } catch (IOException e) {
                view.showErrorDialog("Cannot load item image!");
            }

            final int finalSlotNo = slotNo;

            view.getItemDisplay(slotNo).setSelectButtonListener(e -> {
                machine.addSelection(finalSlotNo);
                DispenseResult result = machine.dispenseSelection();
            
                view.inputLog(result.getProcessMessages().get(0));
                view.inputLog(
                    "Change: P" + result.getChange().getTotal() + "\n"
                );

                // TODO: show flattened total

                if (result.getChange().getTotal() != 0) {
                    view.displayDenominations(
                        "Returned Change:",
                        result.getChange().getQuantityMap()
                    );
                }

                updateItemsView();
                updatePresetsView();
                view.updateTotalCredit(machine.getCredit().getTotal());
            });
            view.getItemDisplay(slotNo).setSpinnerAddListener(e -> {
                machine.addSelection(finalSlotNo);

                updateItemsView();
                view.updateTotalPayment(machine.getTotalPrice());
                view.updateTotalCalories(machine.getTotalCalories());
            });
            view.getItemDisplay(slotNo).setSpinnerRemoveListener(e -> {
                machine.addSelection(finalSlotNo);                
                updateItemsView();                
            });

            slotNo += 1;   
        }
        updateItemsView();

        // Other buttons

        view.setExitButtonListener(e -> {
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

            // TODO: show flattened total

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
                    view.inputLog("\t" + msg);
                }
            
                view.inputLog("Done!\n");

                // Dispensing

                view.inputLog("Dispensed " + result.getName() + ".");
                view.inputLog(
                    "Change: P" + result.getChange().getTotal() + "\n"
                );

                if (result.getChange().getTotal() != 0) {
                    // TODO: show flattened total
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
                }
            }
        });
    }

    /* */

    private void updateItemsView() {
        SpecialVendingMachine machine =
            (SpecialVendingMachine) model.getVendingMachine();

        int slotNo = 1;
        
        for (SpecialSlot slot : machine.getSlots()) {
            view.getItemDisplay(slotNo)
                .setStockValue(slot.getStock());

            view.getItemDisplay(slotNo)
                .setSelectEnable(slot.getStock() == 0);
            view.getItemDisplay(slotNo)
                .setSpinnerVisible(machine.getSelectedPreset() != null);

            if (machine.getSelectedSlots().containsKey(slot)) {
                int selQty = machine.getSelectedSlots().get(slot);

                view.getItemDisplay(slotNo).setSelectValue(selQty);
                view.getItemDisplay(slotNo).setSpinnerMaximum(slot.getStock());
            }

            slotNo++;
        }
    }

    private void updatePresetsView() {
        SpecialVendingMachine machine =
            (SpecialVendingMachine) model.getVendingMachine();

        int presetNo = 1;

        for (Preset preset : machine.getPresets()) {
            view.getPresetDisplay(presetNo)
                .setSelectEnable(machine.hasEnoughStockFor(presetNo));

            if (machine.getSelectedPreset() == preset) {
                view.getPresetDisplay(presetNo)
                    .setHighlightShow(machine.hasDeviatedFrom(presetNo));
            }

            presetNo++;
        }
    }
}
