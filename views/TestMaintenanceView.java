package views;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import util.View;
import views.components.ManageMoneyPanel;
import views.components.SectionContainer;
import views.components.ManageItemsPanel;
import views.components.SummaryViewPanel;

/**
 * This class represents the view for testing the maintenance features of a
 * vending machine. It provides options to view the transaction summary, 
 * manage items, and manage money.
 */
public class TestMaintenanceView extends View {
    /**
     * The section container for the GUI components.
     */
    private SectionContainer mainContainer;

    /**
     * The JTabbedPane to hold the different maintenance feature options.
     */
    private JTabbedPane optionTabs;

    /**
     * The panel to view the summary of the vending machine transactions.
     */
    private SummaryViewPanel summaryViewPanel;

    /**
     * The panel to manage the items in the vending machine.
     */
    private ManageItemsPanel manageItemsPanel;

    /**
     * The panel to manage the money in the vending machine.
     */
    private ManageMoneyPanel manageMoneyPanel;

    /**
     * Constructs a new TestMaintenanceView with its associated GUI components.
     */
    public TestMaintenanceView() {
        mainContainer = new SectionContainer("Test Maintenance Features");

        optionTabs = new JTabbedPane();
        mainContainer.setBody(optionTabs);

        summaryViewPanel = new SummaryViewPanel();
        optionTabs.addTab("View Summary", summaryViewPanel);

        manageItemsPanel = new ManageItemsPanel();
        optionTabs.addTab("Manage Items", manageItemsPanel);

        manageMoneyPanel = new ManageMoneyPanel();
        optionTabs.addTab("Manage Money", manageMoneyPanel);
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    /**
     * Returns the SummaryViewPanel instance used to view the transaction
     * summary.
     * @return the SummaryViewPanel instance.
     */
    public SummaryViewPanel getSummaryViewPanel() {
        return summaryViewPanel;
    }

    /**
     * Returns the ManageItemsPanel instance used to manage the items
     * in the machine.
     * @return the SummaryViewPanel instance.
     */
    public ManageItemsPanel getManageItemsPanel() {
        return manageItemsPanel;
    }

    /**
     * Returns the ManageMoneyPanel instance used to manage the money
     * in the machine.
     * @return the SummaryViewPanel instance.
     */
    public ManageMoneyPanel getManageMoneyPanel() {
        return manageMoneyPanel;
    }

    /**
     * Adds a listener for the exit button.
     * @param listener the listener for the exit button
     */
    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }
}
