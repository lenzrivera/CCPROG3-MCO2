package views;

import views.components.SetupBody;
import views.components.SetupRegularItemsPanel;
import views.templates.CreateMachineView;

public class CreateRegularMachineView extends CreateMachineView<SetupRegularItemsPanel> {
    public CreateRegularMachineView() {
        super("Create a Regular Vending Machine");
        
        setupItemsPanel = new SetupBody<>(new SetupRegularItemsPanel());

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Setup Items", setupItemsPanel);
        setupPane.addTab("Manage Money", manageMoneyPanel);
    }
}
