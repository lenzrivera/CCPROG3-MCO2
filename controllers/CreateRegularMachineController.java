package controllers;

import controllers.templates.CreateMachineController;
import model.Item;
import model.RegularVendingMachine;
import model.Slot;
import model.VendingMachineModel;
import states.MainMenuState;
import views.CreateRegularMachineView;
import views.components.BasicInfoPanel;
import views.components.SetupItemsPanel;

public class CreateRegularMachineController 
    extends CreateMachineController<CreateRegularMachineView> 
{
    private RegularVendingMachine machine;

    public CreateRegularMachineController(
        VendingMachineModel model, 
        CreateRegularMachineView view
    ) {
        super(model, view);

        machine = null;
    }

    @Override
    protected void setListeners() {
        view.setExitButtonListener(e -> {
            // Exit without saving anything.
            changeState(new MainMenuState());
        });

        /* BasicInfoPanel */

        view.getBasicInfoPanel().setNextButtonListener(e -> {
            BasicInfoPanel panel = view.getBasicInfoPanel().getContent();
            
            if (panel.getName().isBlank()) {
                view.showErrorDialog("Please enter a valid name.");
                return;
            }

            machine = new RegularVendingMachine(
                panel.getName(),
                panel.getSlotCount(),
                panel.getSlotCapacity()
            );

            view.getSetItemsPanel()
                .getContent()
                .setMaxStock(panel.getSlotCapacity());
            view.getSetItemsPanel()
                .getContent()
                .setSlotCount(panel.getSlotCount());

            view.getSetupPane().setActiveTab(1);
        });

        /* SetItemsPanel */

        view.getSetItemsPanel().setNextButtonListener(e -> {
            view.getSetupPane().setActiveTab(2);
        });

        view.getSetItemsPanel().getContent().setItemAddListener(e -> {
            SetupItemsPanel panel = view.getSetItemsPanel().getContent();

            if (!checkFieldValidity(machine)) {
                return;
            }

            int slotNo = panel.getSelectedSlotNo();
            String name = panel.getItemNameInput();
            double price = panel.getPriceInput();
            double calories = panel.getCaloriesInput();
            String imagePath = panel.getImagePathInput();
            int stock = panel.getStockInput();

            // In case an item is already in the slot.
            machine.getSlot(slotNo).clearAssignment();

            Item sample = new Item(name, calories, imagePath);
            machine.getSlot(slotNo).assignToItem(sample, price);
            machine.getSlot(slotNo).stockItem(stock);
            
            updateSlotTable(machine.getSlots());  
        });

        view.getSetItemsPanel().getContent().setSlotSelectListener(e -> {
            SetupItemsPanel panel = view.getSetItemsPanel().getContent();

            int selectedSlotNo = panel.getSelectedSlotNo();
            Slot selectedSlot = machine.getSlot(selectedSlotNo);
            Item sampleItem = selectedSlot.getSampleItem();
            
            if (sampleItem == null) {
                panel.setItemNameInput("");
                panel.setCaloriesInput(0.0);
                panel.setPriceInput(0.0);
                panel.setStockInput(0);
                panel.setImagePathInput(null);
            } else {
                panel.setItemNameInput(sampleItem.getName());
                panel.setCaloriesInput(sampleItem.getCalories());
                panel.setPriceInput(selectedSlot.getUnitPrice());
                panel.setStockInput(selectedSlot.getStock());
                panel.setImagePathInput(sampleItem.getImagePath());
            }
        });

        view.getSetItemsPanel().getContent().setItemRemoveListener(e -> {
            int slotNo = view.getSetItemsPanel().getContent().getSelectedSlotNo();

            if (machine.getSlot(slotNo).getSampleItem() == null) {
                view.showErrorDialog("Cannot remove a non-existent item.");
                return;
            }
            
            machine.getSlot(slotNo).clearAssignment();
            updateSlotTable(machine.getSlots());
        });

        /* StockMoneyPanel */

        view.getManageMoneyPanel().setNextButtonListener(e -> {
            model.setVendingMachine(machine);
            changeState(new MainMenuState());
        });

        view.getManageMoneyPanel().getContent().setCollectListener(e -> {

        });

        view.getManageMoneyPanel().getContent().setCollectAllListener(e -> {

        });

        view.getManageMoneyPanel().getContent().setStockListener(e -> {

        });
    }
}