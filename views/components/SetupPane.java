package views.components;

import java.awt.Component;

import javax.swing.JTabbedPane;

/**
 * This class represents a custom JTabbedPane for a setup process. It 
 * automatically disables all tabs except the first one when they are added.
 * It provides a method to set the active tab and enable it while disabling 
 * other tabs.
 */
public class SetupPane extends JTabbedPane {
    /**
     * Creates a new instance of the SetupPane.
     */
    public SetupPane() {
    }
    
    /**
     * Adds a new tab to the setup pane with the given title and component.
     * It automatically disables all tabs except the first one when they are added.
     * @param title the title of the tab.
     * @param component the component to be displayed in the tab.
     */
    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);

        // Disable all tabs except the first one initially.
        if (getTabCount() > 1) {
            setEnabledAt(getTabCount() - 1, false);
        }
    }

    /**
     * Sets the active tab and enables it while disabling other tabs.
     * @param tabIndex the index of the tab to be set as active.
     */
    public void setActiveTab(int tabIndex) {
        for (int i = 0; i < getTabCount(); i++) {
            setEnabledAt(i, false);
        }
    
        setEnabledAt(tabIndex, true);
        setSelectedIndex(tabIndex);
    }
}
