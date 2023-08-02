package views;

import views.components.SetupBody;
import views.components.SetupPresetsPanel;
import views.components.SetupSpecialItemsPanel;
import views.templates.CreateMachineView;

/**
 * This class represents the view for the speical vending machine creation 
 * screen. It extends the CreateMachineView class with a specific type of 
 * setup items panel for special vending machines, and allows for the
 * setup of presets as well.
 */
public class CreateSpecialMachineView 
    extends CreateMachineView<SetupSpecialItemsPanel> 
{
    /**
     * The setup presets panel for configuring presets of a special vending
     * machine.
     */
    private SetupBody<SetupPresetsPanel> setupPresetsPanel;

    /**
     * Constructs a new CreateSpecialMachineView.
     */
    public CreateSpecialMachineView() {
        super("Create a Special Vending Machine");

        setupItemsPanel = new SetupBody<>(new SetupSpecialItemsPanel());
        setupPresetsPanel = new SetupBody<>(new SetupPresetsPanel());

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Setup Items", setupItemsPanel);
        setupPane.addTab("Setup Presets", setupPresetsPanel);
        setupPane.addTab("Manage Money", manageMoneyPanel);
    }

    /**
     * Returns the setup presets panel wrapped in a SetupBody.
     * @return the setup presets panel wrapped in a SetupBody.
     */
    public SetupBody<SetupPresetsPanel> getSetupPresetsPanel() {
        return setupPresetsPanel;
    }
}
