package views;

import views.components.StockRegularItemsPanel;
import views.templates.TestMaintenanceView;

public class TestRegularMaintenanceView 
    extends TestMaintenanceView<StockRegularItemsPanel> 
{
    public TestRegularMaintenanceView() {
        super("Test Regular Machine Maintenance Features");

        stockItemsPanel = new StockRegularItemsPanel();

        optionTabs.addTab("(Re)stock Items", stockItemsPanel);
        optionTabs.addTab("Generate Summary", summaryViewPanel);
        optionTabs.addTab("Manage Money", manageMoneyPanel);
    }   
}
