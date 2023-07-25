package views;

import views.components.SetupBody;
import views.components.StockRegularItemsPanel;
import views.templates.CreateMachineView;

public class CreateRegularMachineView extends CreateMachineView<StockRegularItemsPanel> {
    public CreateRegularMachineView() {
        super("Create a Regular Vending Machine");
        
        stockItemsPanel = new SetupBody<>(new StockRegularItemsPanel());

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Stock Items", stockItemsPanel);
        setupPane.addTab("Stock Change", stockChangePanel);
    }
}
