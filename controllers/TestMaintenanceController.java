package controllers;

import java.io.IOException;
import java.util.List;

import model.Denomination;
import model.DenominationMap;
import model.Slot;
import model.VendingMachine;
import model.VendingMachineModel;
import states.TestMachineMenuState;
import util.Controller;
import views.TestMaintenanceView;
import views.components.ManageMoneyPanel;
import views.components.ManageItemsPanel;

public class TestMaintenanceController extends Controller {
    /**
     * The VendingMachineModel for the whole simulator.
     */
    protected VendingMachineModel model;

    protected TestMaintenanceView view;

    public TestMaintenanceController(
        VendingMachineModel model, 
        TestMaintenanceView view
    ) {
        this.model = model;
        this.view = view;

        VendingMachine<? extends Slot> machine = model.getVendingMachine();

        setListeners();

        view.getManageMoneyPanel()
            .setDenominations(Denomination.getDoubleValues());
        view.getManageItemsPanel()
            .setSlotCapacity(machine.getSlotCapacity());

        updateSlotTable(machine.getSlots());
        updateDenominationTable(machine.getMoneyStock());
    }

    private void setListeners() {
        VendingMachine<? extends Slot> machine = model.getVendingMachine();

        view.setExitButtonListener(e -> {
            changeState(new TestMachineMenuState());
        });

        /* ManageItemsPanel */

        view.getManageItemsPanel().setItemAddListener(e -> {
            int slotNo = view.getManageItemsPanel()
                             .getSlotTable()
                             .getSelectedRowIndex() + 1;

            // If for whatever reason no items have been set to the machine.
            if (slotNo == 0) {
                return;
            }

            Slot selectedSlot = machine.getSlot(slotNo);

            if (selectedSlot.getSampleItem() == null) {
                view.showErrorDialog("Cannot restock a non-existent item.");
                return;
            }

            int qtyToAdd = view.getManageItemsPanel().getQuantityInput();

            try {
                selectedSlot.stockItem(qtyToAdd);
                machine.getSummary().reset(machine.getSlots());

                view.getManageItemsPanel()
                    .setStockLabelText(selectedSlot.getStock());
            } catch (IllegalArgumentException ex) {
                view.showErrorDialog("Cannot exceed slot capacity.");
            } 
        });

        view.getManageItemsPanel().setItemRemoveListener(e -> {
            int slotNo = view.getManageItemsPanel()
                             .getSlotTable()
                             .getSelectedRowIndex() + 1;

            // If for whatever reason no items have been set to the machine.
            if (slotNo == 0) {
                return;
            }

            Slot selectedSlot = machine.getSlot(slotNo);

            if (selectedSlot.getSampleItem() == null) {
                view.showErrorDialog("Cannot remove a non-existent item.");
                return;
            }

            int qtyToRemove = view.getManageItemsPanel().getQuantityInput();

            if (selectedSlot.getStock() - qtyToRemove < 0) {
                view.showErrorDialog(
                    "Cannot remove more than the remaining number of items."
                );
                return;
            }

            for (int i = 0; i < qtyToRemove; i++) {
                selectedSlot.dispenseItem();
            }
            machine.getSummary().reset(machine.getSlots());

            view.getManageItemsPanel()
                .setStockLabelText(selectedSlot.getStock());
        });

        view.getManageItemsPanel().setPriceEditListener(e -> {
            int slotNo = view.getManageItemsPanel()
                             .getSlotTable()
                             .getSelectedRowIndex() + 1;
            
            Slot selectedSlot = machine.getSlot(slotNo);
            
            if (selectedSlot.getSampleItem() == null) {
                view.showErrorDialog(
                    "Cannot set the price of a nonexistent item."
                );
                return;
            }

            double newPrice = view.getManageItemsPanel().getPriceInput();

            if (Denomination.isValidPrice(newPrice)) {
                view.showErrorDialog("Please enter a valid price.");
                return;                
            }
            
            selectedSlot.setUnitPrice(newPrice);
        });

        view.getManageItemsPanel().getSlotTable().setRowSelectListener(e -> {
            int slotNo = view.getManageItemsPanel()
                             .getSlotTable()
                             .getSelectedRowIndex() + 1;
            
            Slot selectedSlot = machine.getSlot(slotNo);
            
            try {
                if (selectedSlot.getSampleItem() != null) {
                    view.getManageItemsPanel()
                        .setItemImage(
                            selectedSlot.getSampleItem().getImagePath()
                        );
                }
                
            } catch (IOException ex) {
                view.showErrorDialog("Cannot load item image!");
            }

            view.getManageItemsPanel().setStockLabelText(selectedSlot.getStock());
            view.getManageItemsPanel().setPriceInput(selectedSlot.getUnitPrice());
        });

        /* ManageMoneyPanel */

        view.getManageMoneyPanel().setCollectListener(e -> {
            ManageMoneyPanel panel = view.getManageMoneyPanel();

            double denom = panel.getSelectedDenom();
            int quantity = panel.getSelectedQuantity();
            
            machine.getMoneyStock().remove(Denomination.toEnum(denom), quantity);
            updateDenominationTable(machine.getMoneyStock()); 
        });

        view.getManageMoneyPanel().setCollectAllListener(e -> {
            machine.getMoneyStock().collect();
            updateDenominationTable(machine.getMoneyStock()); 
        });

        view.getManageMoneyPanel().setStockListener(e -> {
            ManageMoneyPanel panel = view.getManageMoneyPanel();

            double denom = panel.getSelectedDenom();
            int quantity = panel.getSelectedQuantity();

            machine.getMoneyStock().add(Denomination.toEnum(denom), quantity);
            updateDenominationTable(machine.getMoneyStock());
        });
    }

    /* */

    public void updateSlotTable(List<? extends Slot> slots) {
        ManageItemsPanel panel = view.getManageItemsPanel();
        
        for (int i = 0; i < slots.size(); i++) {
            String name = (slots.get(i).getSampleItem() == null) 
                ? "[empty]" 
                : slots.get(i).getSampleItem().getName();

            panel.getSlotTable().setCol0(i, i + 1);
            panel.getSlotTable().setCol1(i, name);
        }
    }

    private void updateDenominationTable(DenominationMap denomMap) {
        view.getManageMoneyPanel().getDenomTable().clearCells();

        int i = 0;

        for (var entry : denomMap.getQuantityMap().entrySet()) {
            view.getManageMoneyPanel()
                .getDenomTable()
                .setCol0(i, entry.getKey().getValue());
            view.getManageMoneyPanel()
                .getDenomTable()
                .setCol1(i, entry.getValue());
            
            i += 1;
        }
    }
}
