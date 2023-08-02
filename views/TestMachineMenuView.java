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
 * This class represents the view for testing the created vending machine.
 * It allows the user to choose between testing vending features or 
 * maintenance features.
 */
public class TestMachineMenuView extends View {
    /**
     * The panel for all the GUI elements
     */
    private JPanel panel;

    /**
     * The heading label for the test machine menu.
     */
    private JLabel heading;

    /**
     * The sub-heading label for the test machine menu.
     */
    private JLabel subHeading;

    /**
     * The button to test vending features.
     */
    private JButton testVendingButton;

    /**
     * The button to test maintenance features.
     */
    private JButton testMaintenanceButton;

    /**
     * The button to go back to the main menu.
     */
    private JButton backButton;

    /**
     * Constructs a new TestMachineMenuView with its associated GUI components.
     */
    public TestMachineMenuView() {
        panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        heading = new JLabel("Test the Created Vending Machine");
        heading.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        heading.setHorizontalAlignment(JLabel.CENTER);

        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(heading, gbc);

        gbc.insets = new Insets(5, 0, 5, 0);

        subHeading = new JLabel("Test what?");
        panel.add(subHeading, gbc);

        testVendingButton = new JButton("Vending Features");
        panel.add(testVendingButton, gbc);

        testMaintenanceButton = new JButton("Maintenance Features");
        panel.add(testMaintenanceButton, gbc);

        backButton = new JButton("Back to Main Menu");
        panel.add(backButton, gbc);
    }

    @Override
    public Container getContainer() {
        return panel;
    }

    /**
     * Adds a listener for the button to test vending features.
     * @param listener the listener for the button to test vending features.
     */
    public void setTestVendingButtonListener(ActionListener listener) {
        testVendingButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the button to test maintenance features.
     * @param listener the listener for the button to test maintenance features.
     */
    public void setTestMaintenanceButtonListener(ActionListener listener) {
        testMaintenanceButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the back button.
     * @param listener the listener for the back button
     */
    public void setBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
