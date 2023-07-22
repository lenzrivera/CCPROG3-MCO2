package views;

import views.components.StockItemsPanel;
import views.components.StockSpecialItemsPanel;
import views.templates.CreateMachineView;

public class CreateSpecialMachineView extends CreateMachineView {
    private StockSpecialItemsPanel stockItemsPanel;

    private SetupPresetsPanel setupPresetsPanel;

    public CreateSpecialMachineView() {
        super("Create a Special Vending Machine");

        stockItemsPanel = new StockSpecialItemsPanel();
        setupPresetsPanel = new SetupPresetsPanel();

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Stock Items", stockItemsPanel);
        setupPane.addTab("Setup Presets", setupPresetsPanel);
        setupPane.addTab("Stock Change", stockChangePanel);
    }

    @Override
    public StockItemsPanel getStockItemsPanel() {
        return stockItemsPanel;
    }

    public SetupPresetsPanel getSetupPresetsPanel() {
        return setupPresetsPanel;
    }
}
