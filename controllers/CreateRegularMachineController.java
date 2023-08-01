package controllers;

import controllers.templates.CreateMachineController;
import model.Denomination;
import model.Item;
import model.RegularVendingMachine;
import model.Slot;
import model.VendingMachineModel;
import states.MainMenuState;
import views.CreateRegularMachineView;
import views.components.BasicInfoPanel;
import views.components.ManageMoneyPanel;
import views.components.SetupItemsPanel;

/**
 * A controller class for managing the creation of a regular vending machine.
 */
public class CreateRegularMachineController 
    extends CreateMachineController<CreateRegularMachineView> 
{
    /**
     * The regular vending machine to create.
     */
    private RegularVendingMachine machine;

    /**
     * Constructs a new CreateRegularMachineController with the provided 
     * VendingMachineModel and CreateRegularMachineView.
     * @param model the VendingMachineModel to be associated with the 
     * controller
     * @param view the CreateRegularMachineView to be associated with the 
     * controller
     */
    public CreateRegularMachineController(
        VendingMachineModel model, 
        CreateRegularMachineView view
    ) {
        super(model, view);

        machine = null;

        view.getBasicInfoPanel().getContent().setNameInput("Pizza VM");
    }

    /**
     * Sets up the event listeners for the view.
     */
    @Override
    protected void setListeners() {
        view.setExitButtonListener(e -> {
            // Exit without saving anything.
            changeState(new MainMenuState());
        });

        /* BasicInfoPanel */

        view.getBasicInfoPanel().setNextButtonListener(e -> {
            BasicInfoPanel panel = view.getBasicInfoPanel().getContent();
            
            if (panel.getNameInput().isBlank()) {
                view.showErrorDialog("Please enter a valid name.");
                return;
            }

            machine = new RegularVendingMachine(
                panel.getNameInput(),
                panel.getSlotCount(),
                panel.getSlotCapacity()
            );

            // TODO: replace w/ actual path
            // TODO: set prices

            machine.getSlot(1).assignToItem(
                new Item("100g Pepperoni", 494, "help/me/pls"), 
                100
            );
            machine.getSlot(2).assignToItem(
                new Item("100g Ham", 145, "help/me/pls"), 
                200
            );
            machine.getSlot(3).assignToItem(
                new Item("100g Ground Pork", 200, "help/me/pls"), 
                300
            );
            machine.getSlot(4).assignToItem(
                new Item("100g Sausage", 301, "help/me/pls"), 
                500
            );
            machine.getSlot(5).assignToItem(
                new Item("50g Shrimp", 28, "help/me/pls"), 
                150
            );
            machine.getSlot(6).assignToItem(
                new Item("1 Slice Pineapple", 42, "help/me/pls"), 
                50.25
            );
            machine.getSlot(7).assignToItem(
                new Item("100g Bell Pepper", 20, "help/me/pls"), 
                25
            );
            machine.getSlot(8).assignToItem(
                new Item("100g Spinach", 23, "help/me/pls"), 
                50
            );
                
            view.getSetItemsPanel()
                .getContent()
                .setMaxStock(panel.getSlotCapacity());

            updateDenominationTable(machine.getMoneyStock());
            updateSlotTable(machine.getSlots());

            view.getSetupPane().setActiveTab(1);
        });

        /* SetItemsPanel */

        view.getSetItemsPanel().setNextButtonListener(e -> {
            machine.getSummary().reset(machine.getSlots());
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

        view.getSetItemsPanel().getContent().getSlotTable().setRowSelectListener(
            e 
        -> {
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
            ManageMoneyPanel panel = view.getManageMoneyPanel().getContent();

            double denom = panel.getSelectedDenom();
            int quantity = panel.getSelectedQuantity();
            
            machine.getMoneyStock().remove(Denomination.toEnum(denom), quantity);
            updateDenominationTable(machine.getMoneyStock()); 
        });

        view.getManageMoneyPanel().getContent().setCollectAllListener(e -> {
            machine.getMoneyStock().collect();
            updateDenominationTable(machine.getMoneyStock()); 
        });

        view.getManageMoneyPanel().getContent().setStockListener(e -> {
            ManageMoneyPanel panel = view.getManageMoneyPanel().getContent();

            double denom = panel.getSelectedDenom();
            int quantity = panel.getSelectedQuantity();

            machine.getMoneyStock().add(Denomination.toEnum(denom), quantity);
            updateDenominationTable(machine.getMoneyStock());
        });
    }
}