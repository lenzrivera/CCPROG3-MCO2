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


    // rename stockitempanel to additempanel
    // fix add item controllers - remove subclasses
        // retain stockitemcontroller for commonality
        // remove subclasses - place directly in respective controllers

    // add stockitempanel placeholder

    protected SummaryViewPanel summaryViewPanel;
    protected ManageMoneyPanel manageMoneyPanel;

    public TestMaintenanceView(String heading) {
        mainContainer = new SectionContainer(heading);

        optionTabs = new JTabbedPane();
        mainContainer.setBody(optionTabs);

        summaryViewPanel = new SummaryViewPanel();

        manageMoneyPanel = new ManageMoneyPanel();
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    // public T getStockItemsPanel() {
    //     return stockItemsPanel;
    // }

    public SummaryViewPanel getSummaryViewPanel() {
        return summaryViewPanel;
    }

    public ManageMoneyPanel getManageMoneyPanel() {
        return manageMoneyPanel;
    }

    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }
}
