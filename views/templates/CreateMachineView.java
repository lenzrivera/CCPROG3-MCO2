package views.templates;

import java.awt.Container;

import util.View;
import views.components.BasicInfoPanel;
import views.components.SectionContainer;
import views.components.SetupPane;
import views.components.StockChangePanel;
import views.components.StockItemsPanel;

public abstract class CreateMachineView<T extends StockItemsPanel> extends View {
    protected SectionContainer mainContainer;

    protected SetupPane setupPane;

    protected BasicInfoPanel basicInfoPanel;
    protected T stockItemsPanel;
    protected StockChangePanel stockChangePanel;

    public CreateMachineView(String heading) {
        mainContainer = new SectionContainer(heading);

        setupPane = new SetupPane();
        mainContainer.add(setupPane);

        basicInfoPanel = new BasicInfoPanel();
        stockChangePanel = new StockChangePanel();
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    public BasicInfoPanel getBasicInfoPanel() {
        return basicInfoPanel;
    }

    public SetupPane getSetupPane() {
        return setupPane;
    }

    public T getStockItemsPanel() {
        return stockItemsPanel;
    }

    public StockChangePanel getStockChangePanel() {
        return stockChangePanel;
    }
}
