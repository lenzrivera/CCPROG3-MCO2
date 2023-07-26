package controllers;

import controllers.templates.CreateMachineController;
import model.RegularVendingMachine;
import model.VendingMachineModel;
import views.CreateRegularMachineView;
import views.components.BasicInfoPanel;

public class CreateRegularMachineController extends CreateMachineController<
    CreateRegularMachineView, RegularVendingMachine> 
{
    public CreateRegularMachineController(
        VendingMachineModel m, 
        CreateRegularMachineView v
    ) {
        super(m, v);
    }

    @Override
    protected void handleBasicInfoNext() {
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

        view.getStockItemsPanel()
            .getContent()
            .setMaxStock(panel.getSlotCapacity());
        view.getStockItemsPanel()
            .getContent()
            .setSlotCount(panel.getSlotCount());
        
        stockItemsController = new StockRegularItemsController(
            machine, 
            view.getStockItemsPanel().getContent(),
            view
        );

        view.getSetupPane().setActiveTab(1);
    }

    @Override
    protected void handleStockItemsNext() {
        view.getSetupPane().setActiveTab(2);
    }
}