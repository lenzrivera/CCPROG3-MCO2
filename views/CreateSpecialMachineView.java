package views;

import views.components.SetupBody;
import views.components.SetupPresetsPanel;
import views.components.SetupSpecialItemsPanel;
import views.templates.CreateMachineView;

public class CreateSpecialMachineView extends CreateMachineView<SetupSpecialItemsPanel> {
    private SetupBody<SetupPresetsPanel> setupPresetsPanel;

    public CreateSpecialMachineView() {
        super("Create a Special Vending Machine");

        setupItemsPanel = new SetupBody<>(new SetupSpecialItemsPanel());
        setupPresetsPanel = new SetupBody<>(new SetupPresetsPanel());

        setupPane.addTab("Basic Information", basicInfoPanel);
        setupPane.addTab("Setup Items", setupItemsPanel);
        setupPane.addTab("Setup Presets", setupPresetsPanel);
        setupPane.addTab("Manage Money", manageMoneyPanel);
    }

    public SetupBody<SetupPresetsPanel> getSetupPresetsPanel() {
        return setupPresetsPanel;
    }
}
