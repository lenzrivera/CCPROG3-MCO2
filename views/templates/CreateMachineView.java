package views.templates;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

import util.View;
import views.components.BasicInfoPanel;
import views.components.SectionContainer;
import views.components.SetupPane;
import views.components.StockChangePanel;
import views.components.StockItemsPanel;

public abstract class CreateMachineView extends View {
    protected SectionContainer mainContainer;

    protected JPanel setupPaneContainer;
    protected SetupPane setupPane;

    protected BasicInfoPanel basicInfoPanel;
    protected StockChangePanel stockChangePanel;

    public CreateMachineView(String heading) {
        mainContainer = new SectionContainer(heading);

        setupPaneContainer = new JPanel();
        mainContainer.setBody(setupPaneContainer);
        
        // Remove the indentation given to the heading by default.
        setupPaneContainer.setAlignmentX(Component.LEFT_ALIGNMENT); 
        // Make the tabbed pane take all the window space.
        setupPaneContainer.setLayout(new GridLayout());

        setupPane = new SetupPane();
        setupPaneContainer.add(setupPane);

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

    public abstract StockItemsPanel getStockItemsPanel();

    public StockChangePanel getStockChangePanel() {
        return stockChangePanel;
    }
}
