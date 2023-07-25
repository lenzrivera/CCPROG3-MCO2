package views.templates;

import java.awt.Container;
import java.awt.event.ActionListener;

import util.View;
import views.components.BasicInfoPanel;
import views.components.SectionContainer;
import views.components.SetupBody;
import views.components.SetupPane;
import views.components.StockChangePanel;
import views.components.StockItemsPanel;

public abstract class CreateMachineView<T extends StockItemsPanel> extends View {
    protected SectionContainer mainContainer;

    protected SetupPane setupPane;

    protected SetupBody<BasicInfoPanel> basicInfoPanel;
    protected SetupBody<T> stockItemsPanel;
    protected SetupBody<StockChangePanel> stockChangePanel;

    public CreateMachineView(String heading) {
        mainContainer = new SectionContainer(heading);

        setupPane = new SetupPane();
        mainContainer.add(setupPane);

        basicInfoPanel = new SetupBody<>(new BasicInfoPanel());
        stockChangePanel = new SetupBody<>(new StockChangePanel());
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    public SetupBody<BasicInfoPanel> getBasicInfoPanel() {
        return basicInfoPanel;
    }

    public SetupPane getSetupPane() {
        return setupPane;
    }

    public SetupBody<T> getStockItemsPanel() {
        return stockItemsPanel;
    }

    public SetupBody<StockChangePanel> getStockChangePanel() {
        return stockChangePanel;
    }

    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }
}
