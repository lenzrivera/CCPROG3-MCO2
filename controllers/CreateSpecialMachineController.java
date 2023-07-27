package controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controllers.templates.CreateMachineController;
import model.Operation;
import model.Slot;
import model.SpecialVendingMachine;
import model.VendingMachineModel;
import views.CreateSpecialMachineView;
import views.components.BasicInfoPanel;

public class CreateSpecialMachineController extends CreateMachineController<
    CreateSpecialMachineView, SpecialVendingMachine> 
{
    private SetupPresetsController presetsController;

    public CreateSpecialMachineController(
        VendingMachineModel m, 
        CreateSpecialMachineView v
    ) {
        super(m, v);

        // Can't be initialized immediately as the machine to control does not
        // exist yet at initialization.
        presetsController = null;
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        /* SetupPresetsPanel */

        view.getSetupPresetsPanel().setNextButtonListener(e -> {
            if (machine.getPresets().size() == 0) {
                view.showErrorDialog("At least one default preset should exist.");
                return;                
            }
            
            view.getSetupPane().setActiveTab(3);
        });
    }

    @Override
    protected void handleBasicInfoNext() {
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

        view.getStockItemsPanel()
            .getContent()
            .setMaxStock(panel.getSlotCapacity());
        view.getStockItemsPanel()
            .getContent()
            .setSlotCount(panel.getSlotCount());

        stockItemsController = new StockSpecialItemsController(
            machine, 
            view.getStockItemsPanel().getContent(),
            view
        );

        view.getSetupPane().setActiveTab(1);
    }

    @Override
    protected void handleStockItemsNext() {
        boolean hasItems = false;

        for (Slot slot : machine.getSlots()) {
            if (slot.getSampleItem() != null) {
                hasItems = true;
                break;
            }
        }

        if (hasItems) {
            presetsController = new SetupPresetsController(
                machine,
                view.getSetupPresetsPanel().getContent(),
                view
            );
            view.getSetupPane().setActiveTab(2);
        } else {
            view.getSetupPane().setActiveTab(3);
        }
    }
}
