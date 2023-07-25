package views.templates;

import java.awt.Container;
import javax.swing.JTabbedPane;

import util.View;
import views.components.ManageMoneyPanel;
import views.components.SectionContainer;
import views.components.StockItemsPanel;
import views.components.SummaryViewPanel;

public class TestMaintenanceView<T extends StockItemsPanel> extends View {
    protected SectionContainer mainContainer;

    protected JTabbedPane optionTabs;

    protected T stockItemsPanel;
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

    public T getStockItemsPanel() {
        return stockItemsPanel;
    }

    public SummaryViewPanel getSummaryViewPanel() {
        return summaryViewPanel;
    }

    public ManageMoneyPanel getManageMoneyPanel() {
        return manageMoneyPanel;
    }
}
