package views.components;

import java.awt.Component;

import javax.swing.JTabbedPane;

public class SetupPane extends JTabbedPane {
    public SetupPane() {
    }
    
    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);

        // Disable all tabs except the first one initially.
        if (getTabCount() > 1) {
            setEnabledAt(getTabCount() - 1, false);
        }
    }

    public void setActiveTab(int tabIndex) {
        for (int i = 0; i < getTabCount(); i++) {
            setEnabledAt(i, false);
        }
    
        setEnabledAt(tabIndex, true);
        setSelectedIndex(tabIndex);
    }
}
