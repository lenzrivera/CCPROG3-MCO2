package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Represents the interface to enter basic information about a vending machine.
 * It provides fields to enter the name, number of slots, and slot capacity.
 */
public class BasicInfoPanel extends JPanel {
    /**
     * The label for the heading of the basic information section.
     */
    private JLabel heading;

    /**
     * The label for the "Name" input field.
     */
    private JLabel nameLabel;

    /**
     * The text input field for the name of the vending machine.
     */
    private JTextField nameInput;

    /**
     * The label for the "Number of slots" input field.
     */
    private JLabel slotCountLabel;

    /**
     * The spinner input field for specifying the number of slots in 
     * the vending machine.
     */
    private JSpinner slotCountInput;

    /**
     * The label for the "Slot capacity" input field.
     */
    private JLabel slotCapacityLabel;

    /**
     * The spinner input field for specifying the capacity of each slot
     * in the vending machine.
     */
    private JSpinner slotCapacityInput;

    /**
     * Constructs a new BasicInfoPanel with its associated GUI components.
     */
    public BasicInfoPanel() {
        super(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;

        heading = new JLabel("<html><u>Enter Basic Information</u></html>");
        gbc.gridwidth = 2;        
        add(heading, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        nameLabel = new JLabel("Name: ");
        gbc.gridx = 0;
        add(nameLabel, gbc);

        nameInput = new JTextField(15);
        gbc.gridx = 1;
        add(nameInput, gbc);

        gbc.gridy = 2;

        slotCountLabel = new JLabel("Number of slots: ");
        gbc.gridx = 0;
        add(slotCountLabel, gbc);

        slotCountInput = new JSpinner();
        gbc.gridx = 1;
        add(slotCountInput, gbc);

        gbc.gridy = 3;

        slotCapacityLabel = new JLabel("Slot capacity: ");
        gbc.gridx = 0;
        add(slotCapacityLabel, gbc);

        slotCapacityInput = new JSpinner();
        gbc.gridx = 1;
        add(slotCapacityInput, gbc);
        
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
    }

    /**
     * Gets the value of the name input field.
     * @return the name entered in the name input field.
     */
    public String getNameInput() {
        return nameInput.getText();
    }

    /**
     * Sets the value of the name input field.
     * @param name the name to set in the name input field.
     */
    public void setNameInput(String name) {
        nameInput.setText(name);
    }

    /**
     * Gets the number of slots entered in the slot count input field.
     * @return the number of slots.
     */
    public int getSlotCount() {
        return (int) slotCountInput.getValue();
    }

    /**
     * Sets the value of the slot count input field.
     * @param count the count to set in the count input field.
     */
    public void setSlotCount(int count) {
        slotCountInput.setValue(count);
    }

    /**
     * Gets the slot capacity entered in the slot capacity input field.
     * @return the slot capacity.
     */
    public int getSlotCapacity() {
        return (int) slotCapacityInput.getValue();
    }

    /**
     * Sets the minimum value that can be entered in the slot count input
     * field.
     * @param minSlotCount the minimum value for the slot count field.
     */
    public void setMinSlotCount(int minSlotCount) {
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setMinimum(minSlotCount);  
        model.setValue(minSlotCount);

        slotCountInput.setModel(model);
    }

    /**
     * Sets the minimum value that can be entered in the slot capacity input
     * field.
     * @param minSlotCapacity The minimum value for the slot capacity input
     * field.
     */
    public void setMinSlotCapacity(int minSlotCapacity) {
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setMinimum(minSlotCapacity);  
        model.setValue(minSlotCapacity);

        slotCapacityInput.setModel(model);
    }
}
