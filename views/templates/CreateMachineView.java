package views.templates;

import java.awt.Container;
import java.awt.event.ActionListener;

import util.View;
import views.components.BasicInfoPanel;
import views.components.ManageMoneyPanel;
import views.components.SectionContainer;
import views.components.SetupBody;
import views.components.SetupPane;
import views.components.SetupItemsPanel;

public abstract class CreateMachineView<T extends SetupItemsPanel> extends View {
    protected SectionContainer mainContainer;

    protected SetupPane setupPane;

    protected SetupBody<BasicInfoPanel> basicInfoPanel;
    protected SetupBody<T> setupItemsPanel;
    protected SetupBody<ManageMoneyPanel> manageMoneyPanel;

    public CreateMachineView(String heading) {
        mainContainer = new SectionContainer(heading);

        setupPane = new SetupPane();
        mainContainer.add(setupPane);

        basicInfoPanel = new SetupBody<>(new BasicInfoPanel());
        manageMoneyPanel = new SetupBody<>(new ManageMoneyPanel());
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

    public SetupBody<T> getSetItemsPanel() {
        return setupItemsPanel;
    }

    public SetupBody<ManageMoneyPanel> getManageMoneyPanel() {
        return manageMoneyPanel;
    }

    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }
}
