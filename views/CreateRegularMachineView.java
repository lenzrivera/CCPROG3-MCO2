package views;

import views.components.SetupBody;
import views.components.SetupRegularItemsPanel;
import views.templates.CreateMachineView;

/**
 * This class represents the view for the regular vending machine creation 
 * screen. It extends the CreateMachineView class with a specific type of 
 * setup items panel for regular vending machines.
 */
public class CreateRegularMachineView extends CreateMachineView<SetupRegularItemsPanel> {
    /**
     * Constructs a new CreateRegularMachineView.
     */
    public CreateRegularMachineView() {
        super("Create a Regular Vending Machine");
        
        setupItemsPanel = new SetupBody<>(new SetupRegularItemsPanel());

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Setup Items", setupItemsPanel);
        setupPane.addTab("Manage Money", manageMoneyPanel);
    }
}
