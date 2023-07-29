package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controllers.templates.CreateMachineController;
import model.Item;
import model.Operation;
import model.Preset;
import model.Slot;
import model.SpecialSlot;
import model.SpecialVendingMachine;
import model.VendingMachineModel;
import states.MainMenuState;
import views.CreateSpecialMachineView;
import views.components.BasicInfoPanel;
import views.components.SetupPresetsPanel;
import views.components.SetupSpecialItemsPanel;

public class CreateSpecialMachineController
    extends CreateMachineController<CreateSpecialMachineView> 
{
    private SpecialVendingMachine machine;

    public CreateSpecialMachineController(
        VendingMachineModel model, 
        CreateSpecialMachineView view
    ) {
        super(model, view);

        machine = null;
    }

    @Override
    protected void setConstants() {
        super.setConstants();

        List<String> opStrings = Stream.of(Operation.values())
                                       .map(Operation::toString)
                                       .collect(Collectors.toList());

        view.getSetItemsPanel().getContent().setOperations(opStrings);
        view.getSetupPresetsPanel().getContent().setOperations(opStrings);
    }

    @Override
    protected void setListeners() {
        view.setExitButtonListener(e -> {
            if (machine.getPresets().size() == 0) {
                view.showErrorDialog("At least one default preset should exist.");
                return;                
            }
            
            view.getSetupPane().setActiveTab(3);
        });

        /* BasicInfoPanel */

        view.getBasicInfoPanel().setNextButtonListener(e -> {
            BasicInfoPanel panel = view.getBasicInfoPanel().getContent();
            
            if (panel.getName().isBlank()) {
                view.showErrorDialog("Please enter a valid name.");
                return;
            }

            machine = new SpecialVendingMachine(
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
            updateSlotTable(machine.getSlots());

            view.getSetupPane().setActiveTab(1);            
        });

        /* AddItemsPanel */

        view.getSetItemsPanel().setNextButtonListener(e -> {
            for (Slot slot : machine.getSlots()) {
                if (slot.getSampleItem() != null) {
                    setItemList(machine.getSlots());
                    view.getSetupPane().setActiveTab(2);
                    break;
                }
            }

            view.getSetupPane().setActiveTab(3);
        });

        view.getSetItemsPanel().getContent().setItemAddListener(e -> {
            SetupSpecialItemsPanel panel = view.getSetItemsPanel().getContent();

            if (!checkFieldValidity(machine)) {
                return;
            }

            int slotNo = panel.getSelectedSlotNo();
            String name = panel.getItemNameInput();
            double price = panel.getPriceInput();
            double calories = panel.getCaloriesInput();
            String imagePath = panel.getImagePathInput();
            boolean standalone = panel.getStandaloneInput();
            String operation = panel.getOperationInput();
            int stock = panel.getStockInput();
        
            // In case an item is already in the slot.
            machine.getSlot(slotNo).clearAssignment();;

            Item sample = new Item(name, calories, imagePath);
            machine.getSlot(slotNo).assignToItem(
                sample, 
                price, 
                standalone, 
                standalone, 
                Operation.valueOf(operation)
            );
            machine.getSlot(slotNo).stockItem(stock);
            
            updateSlotTable(machine.getSlots());            
        });

        view.getSetItemsPanel().getContent().setSlotSelectListener(e -> {
            SetupSpecialItemsPanel panel = view.getSetItemsPanel().getContent();

            int selectedSlotNo = panel.getSelectedSlotNo();
            SpecialSlot selectedSlot = machine.getSlot(selectedSlotNo);
            Item sampleItem = selectedSlot.getSampleItem();
            
            if (sampleItem == null) {
                panel.setItemNameInput("");
                panel.setCaloriesInput(0.0);
                panel.setPriceInput(0.0);
                panel.setStockInput(0);
                panel.setImagePathInput(null);
                panel.setStandaloneInput(true);
                panel.setOperationInput(0);
            } else {
                panel.setItemNameInput(sampleItem.getName());
                panel.setCaloriesInput(sampleItem.getCalories());
                panel.setPriceInput(selectedSlot.getUnitPrice());
                panel.setStockInput(selectedSlot.getStock());
                panel.setImagePathInput(sampleItem.getImagePath());
                panel.setStandaloneInput(selectedSlot.isStandalone());
                panel.setOperationInput(selectedSlot.getOperation().toString());
            }
        });

        view.getSetItemsPanel().getContent().setItemRemoveListener(e -> {
            SetupSpecialItemsPanel panel = view.getSetItemsPanel().getContent();
            int slotNo = panel.getSelectedSlotNo();

            if (machine.getSlot(slotNo).getSampleItem() == null) {
                view.showErrorDialog("Cannot remove a non-existent item.");
                return;
            }

            String itemName = machine.getSlot(slotNo).getSampleItem().getName();

            for (Preset preset : machine.getPresets()) {
                if (preset.getItems().containsKey(itemName)) {
                    view.showErrorDialog(
                        "Cannot remove item being used in a preset.");
                    return;
                }
            }        
                
            machine.getSlot(slotNo).clearAssignment();
            updateSlotTable(machine.getSlots());
        });

        /* SetupPresetsPanel */

        view.getSetupPresetsPanel().setNextButtonListener(e -> {
            if (machine.getPresets().size() == 0) {
                view.showErrorDialog("At least one default preset should exist.");
                return;                
            }
            
            view.getSetupPane().setActiveTab(3);
        });

        view.getSetupPresetsPanel().getContent().setPresetAddListener(e -> {
            // TODO: perhaps look into invalid presets (e.g. only toppings) 

            SetupPresetsPanel panel = view.getSetupPresetsPanel().getContent();
            
            int presetIndex = panel.getSelectedPresetIndex();
            String name = panel.getNameInput();
            Map<String, Integer> items = panel.getItemMapping();
            String operation = panel.getOperationInput();
            String imagePath = panel.getImagePath();

            if (name.isBlank()) {
                view.showErrorDialog("Please enter a valid preset name.");
                return;
            }
            
            for (int i = 0; i < machine.getPresets().size(); i++) {
                Preset preset = machine.getPresets().get(i);
    
                if (
                    i != presetIndex && 
                    preset != null && 
                    preset.getName().equalsIgnoreCase(name)    
                ) {
                    view.showErrorDialog("Please enter an unused preset name.");
                    return;
                }
            }

            if (items.size() == 0) {
                view.showErrorDialog("A preset cannot have no items.");
                return;                
            }

            boolean hasBaseItem = false;

            for (SpecialSlot slot : machine.getSlots()) {
                if (
                    slot.isBase() && slot.isStandalone() && 
                    items.containsKey(slot.getSampleItem().getName())
                ) {
                    hasBaseItem = true;
                    break;
                }
            }

            if (!hasBaseItem) {
                view.showErrorDialog(
                    "A preset cannot have no standalone base items."
                );
                return;                   
            }

            if (imagePath.isBlank()) {
                view.showErrorDialog("Please select a preset image.");
                return;                
            }

            Preset newPreset = new Preset(
                name, items, Operation.valueOf(operation), imagePath);

            if (presetIndex == -1) {
                machine.getPresets().add(newPreset);
            } else {
                machine.getPresets().set(presetIndex, newPreset);
            }

            updatePresetList();           
        });

        view.getSetupPresetsPanel().getContent().setPresetRemoveListener(e -> {
            SetupPresetsPanel panel = view.getSetupPresetsPanel().getContent();

            machine.getPresets().remove(panel.getSelectedPresetIndex());
            updatePresetList();
        });

        view.getSetupPresetsPanel().getContent().setPresetSelectListener(e -> {
            SetupPresetsPanel panel = view.getSetupPresetsPanel().getContent();

            int selectedIndex = panel.getSelectedPresetIndex();
            
            if (selectedIndex == -1) {
                panel.setNameInputValue("");
                panel.setImagePathValue("");
                panel.setOperationValue(0);
                panel.updateItemMap(new HashMap<>());
                return;
            }

            Preset preset = machine.getPresets().get(selectedIndex);
            panel.setNameInputValue(preset.getName());
            panel.setImagePathValue(preset.getImagePath());
            panel.setOperationValue(preset.getOperation().toString());

            panel.updateItemMap(preset.getItems());
        });

        view.getSetupPresetsPanel().getContent().setPresetSelectListener(e -> {
            SetupPresetsPanel panel = view.getSetupPresetsPanel().getContent();
            
            int selectedIndex = panel.getSelectedPresetIndex();
            
            if (selectedIndex == -1) {
                panel.setNameInputValue("");
                panel.setImagePathValue("");
                panel.setOperationValue(0);
                panel.updateItemMap(new HashMap<>());
                return;
            }

            Preset preset = machine.getPresets().get(selectedIndex);
            panel.setNameInputValue(preset.getName());
            panel.setImagePathValue(preset.getImagePath());
            panel.setOperationValue(preset.getOperation().toString());

            panel.updateItemMap(preset.getItems());
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

    /* */

    private void setItemList(List<? extends Slot> slots) {
        SetupPresetsPanel panel = view.getSetupPresetsPanel().getContent();

        panel.clearItemQtySelectors();

        for (Slot slot : slots) {
            if (slot.getSampleItem() == null) {
                continue;
            }

            panel.addItemQtySelector(slot.getSampleItem().getName());
        }
    }

    private void updatePresetList() {
        SetupPresetsPanel panel = view.getSetupPresetsPanel().getContent();
        
        panel.clearPresetList();

        for (Preset preset : machine.getPresets()) {
            panel.addPreset(preset.getName());
        }
    }
}
