package views;

import java.awt.Container;

import views.components.StockRegularItemsPanel;
import views.templates.CreateMachineView;

public class CreateRegularMachineView extends CreateMachineView {
    private StockRegularItemsPanel stockItemsPanel;

    public CreateRegularMachineView() {
        super("Create a Regular Vending Machine");
        
        stockItemsPanel = new StockRegularItemsPanel();

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Stock Items", stockItemsPanel);
        setupPane.addTab("Stock Change", stockChangePanel);
    }

    @Override
    public Container getContainer() {
        return mainContainer;
    }

    public StockRegularItemsPanel getStockItemsPanel() {
        return stockItemsPanel;
    }
}
