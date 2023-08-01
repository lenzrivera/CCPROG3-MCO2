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
 * This class represents the view for the machine creation menu. This view 
 * allows the user to choose between creating a regular vending machine 
 * or a special vending machine.
 */
public class CreateMachineMenuView extends View {
    /**
     * The main panel for the machine creation menu.
     */
    private JPanel panel;

    /**
     * The heading label displaying "Create a Vending Machine".
     */
    private JLabel heading;

    /**
     * The sub-heading label displaying "What kind?".
     */
    private JLabel subHeading;

    /**
     * The button to create a regular vending machine.
     */
    private JButton createRegularButton;
    
    /**
     * The button to create a special vending machine.
     */
    private JButton createSpecialButton;

    /**
     * The button to go back to the main menu.
     */
    private JButton backButton;

    /**
     * Constructs a new CreateMachineMenuView instance, setting up the
     * GUI components and layout.
     */
    public CreateMachineMenuView() {
        super();

        panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        heading = new JLabel("Create a Vending Machine");
        heading.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        heading.setHorizontalAlignment(JLabel.CENTER);

        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(heading, gbc);

        gbc.insets = new Insets(5, 0, 5, 0);

        subHeading = new JLabel("<html><u>What kind?</u></html>");
        panel.add(subHeading, gbc);

        createRegularButton = new JButton("Regular Vending Machine");
        panel.add(createRegularButton, gbc);

        createSpecialButton = new JButton("Special Vending Machine");
        panel.add(createSpecialButton, gbc);

        backButton = new JButton("Back to Main Menu");
        panel.add(backButton, gbc);
    }

    @Override
    public Container getContainer() {
        return panel;
    }

    /**
     * Sets the listener for the button for creating a regular
     * vending machine.
     * @param listener The listener to be set for the button.
     */
    public void setCreateRegularButtonListener(ActionListener listener) {
        createRegularButton.addActionListener(listener);
    }

    /**
     * Sets the listener for the button for creating a special
     * vending machine.
     * @param listener The listener to be set for the button.
     */
    public void setCreateSpecialButtonListener(ActionListener listener) {
        createSpecialButton.addActionListener(listener);
    }

    /**
     * Sets the listener for the back button.
     * @param listener The listener to be set for the button.
     */
    public void setBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
