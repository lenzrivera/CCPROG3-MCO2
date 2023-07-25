package views;

import views.components.SetupPresetsPanel;
import views.components.StockSpecialItemsPanel;
import views.templates.TestMaintenanceView;

public class TestSpecialMaintenanceView 
    extends TestMaintenanceView<StockSpecialItemsPanel>
{
    private SetupPresetsPanel setupPresetsPanel;

    public TestSpecialMaintenanceView() {
        super("Test Special Machine Maintenance Features");

        stockItemsPanel = new StockSpecialItemsPanel();
        setupPresetsPanel = new SetupPresetsPanel();

        optionTabs.addTab("(Re)stock Items", stockItemsPanel);
        optionTabs.addTab("Setup Presets", setupPresetsPanel);
        optionTabs.addTab("Generate Summary", summaryViewPanel);
        optionTabs.addTab("Manage Money", manageMoneyPanel);
    }

    public SetupPresetsPanel getSetupPresetsPanel() {
        return setupPresetsPanel;
    }
}
