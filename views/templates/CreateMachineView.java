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

/**
 * This class represents the view for the machine creation menus. It provides
 * common GUI components and layouts for the regular and special vending 
 * machine creation menus.
 * @param <T> The type of the setup items panel specific to the subclass.
 */
public abstract class CreateMachineView<
    T extends SetupItemsPanel> extends View 
{
    /**
     * The main container for the view.
     */
    protected SectionContainer mainContainer;

    /**
     * The setup pane containing different setup sections.
     */
    protected SetupPane setupPane;

    /**
     * The basic info panel wrapped in a SetupBody to facilitate the setup 
     * progression.
     */
    protected SetupBody<BasicInfoPanel> basicInfoPanel;

    /**
     * The setup items panel wrapped in a SetupBody to facilitate the setup 
     * progression.
     */
    protected SetupBody<T> setupItemsPanel;
    
    /**
     * The manage money panel wrapped in a SetupBody to facilitate the setup 
     * progression.
     */
    protected SetupBody<ManageMoneyPanel> manageMoneyPanel;

    /**
     * Constructs a new CreateMachineView with the specified heading to 
     * display.
     * @param heading The heading to be displayed in the setup view.
     */
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

    /**
     * Returns the basic info panel wrapped around a SetupBody.
     * @return the basic info panel wrapped around a SetupBody.
     */
    public SetupBody<BasicInfoPanel> getBasicInfoPanel() {
        return basicInfoPanel;
    }

    /**
     * Returns the setup pane that contains the different setup sections.
     * @return the setup pane.
     */
    public SetupPane getSetupPane() {
        return setupPane;
    }

    /**
     * Returns the set items panel wrapped around a SetupBody.
     * @return the set items panel wrapped around a SetupBody.
     */
    public SetupBody<T> getSetItemsPanel() {
        return setupItemsPanel;
    }

    /**
     * Returns the manage money panel wrapped around a SetupBody.
     * @return the manage money panel wrapped around a SetupBody.
     */
    public SetupBody<ManageMoneyPanel> getManageMoneyPanel() {
        return manageMoneyPanel;
    }

    /**
     * Sets the ActionListener for the exit button for this section.
     * @param listener The listener to be set for the exit button.
     */
    public void setExitButtonListener(ActionListener listener) {
        mainContainer.setExitButtonListener(listener);
    }
}
