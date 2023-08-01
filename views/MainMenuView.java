package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.View;

/**
 * This class represents the view for the main menu of the simulator. It
 * presents the interface for the user to pick between creating a vending
 * machine, testing a vending machine, or exiting the simulator.
 */
public class MainMenuView extends View {
    /**
     * The panel for the GUI components.
     */
    private JPanel panel;

    /**
     * The heading label for the main menu.
     */
    private JLabel heading;

    /**
     * The button to create a vending machine.
     */
    private JButton createButton;

    /**
     * The button to test a vending machine.
     */
    private JButton testButton;

    /**
     * The button to exit the simulator.
     */
    private JButton exitButton;

    /**
     * Constructs a new MainMenuView with its GUI components.
     */
    public MainMenuView() {
        panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        heading = new JLabel("Vending Machine Factory!");
        heading.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        heading.setHorizontalAlignment(JLabel.CENTER);

        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(heading, gbc);

        gbc.insets = new Insets(5, 0, 5, 0);

        createButton = new JButton("Create Vending Machine");
        panel.add(createButton, gbc);

        testButton = new JButton("Test Vending Machine");
        panel.add(testButton, gbc);

        exitButton = new JButton("Exit Simulator");
        panel.add(exitButton, gbc);
    }

    @Override
    public Container getContainer() {
        return panel;
    }

    /**
     * Adds a listener for the button to create a regular vending machine.
     * @param listener the listener for the create regular vending machine button.
     */
    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the button to exit the simulator.
     * @param listener the listener for the button to exit the simulator.
     */
    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    /**
     * Sets the enabled status of the test vending machine button.
     * @param v true to enable the button, false to disable it.
     */
    public void setTestButtonEnabled(boolean v) {
        testButton.setEnabled(v);
    }

    /**
     * Adds a listener for the button to test a vending machine.
     * @param listener the listener for the button to test a vending machine.
     */
    public void setTestButtonListener(ActionListener listener) {
        testButton.addActionListener(listener);
    }

    /**
     * Sets the tooltip text for the test vending machine button.
     * @param tooltip the tooltip text to be set for the button.
     */
    public void setTestButtonToolTip(String tooltip) {
        testButton.setToolTipText(tooltip);
    }
}
