package views;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import util.View;
import views.components.ManageMoneyPanel;
import views.components.SectionContainer;
import views.components.StockItemsPanel;
import views.components.SummaryViewPanel;

public class TestMaintenanceView extends View {
    protected SectionContainer mainContainer;

    protected JTabbedPane optionTabs;

    protected SummaryViewPanel summaryViewPanel;
    protected StockItemsPanel stockItemsPanel;
    protected ManageMoneyPanel manageMoneyPanel;

    public TestMaintenanceView() {
        mainContainer = new SectionContainer("Test Maintenance Features");

        optionTabs = new JTabbedPane();
        mainContainer.setBody(optionTabs);

        summaryViewPanel = new SummaryViewPanel();
        optionTabs.addTab("View Summary", summaryViewPanel);

        stockItemsPanel = new StockItemsPanel();
        optionTabs.addTab("Restock Items", summaryViewPanel);

        manageMoneyPanel = new ManageMoneyPanel();
        optionTabs.addTab("Manage Money", summaryViewPanel);
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    public SummaryViewPanel getSummaryViewPanel() {
        return summaryViewPanel;
    }

    public StockItemsPanel getStockItemsPanel() {
        return stockItemsPanel;
    }

    public ManageMoneyPanel getManageMoneyPanel() {
        return manageMoneyPanel;
    }

    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }
}
