package views;

import views.components.SetupBody;
import views.components.SetupPresetsPanel;
import views.components.StockSpecialItemsPanel;
import views.templates.CreateMachineView;

public class CreateSpecialMachineView extends CreateMachineView<StockSpecialItemsPanel> {
    private SetupBody<SetupPresetsPanel> setupPresetsPanel;

    public CreateSpecialMachineView() {
        super("Create a Special Vending Machine");

        stockItemsPanel = new SetupBody<>(new StockSpecialItemsPanel());
        setupPresetsPanel = new SetupBody<>(new SetupPresetsPanel());

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Stock Items", stockItemsPanel);
        setupPane.addTab("Setup Presets", setupPresetsPanel);
        setupPane.addTab("Stock Change", stockChangePanel);
    }

    public SetupBody<SetupPresetsPanel> getSetupPresetsPanel() {
        return setupPresetsPanel;
    }
}
