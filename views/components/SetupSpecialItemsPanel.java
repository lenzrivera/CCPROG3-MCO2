package views.components;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * This class represents the setup items panel for the special vending machine.
 * It provides additional fields for standalone status and operation selection.
 */
public class SetupSpecialItemsPanel extends SetupItemsPanel {
    /** 
     * Label for the standalone status checkbox. 
     */
    private JLabel standaloneLabel;
    
    /** 
     * Checkbox for marking an item as standalone. 
     */
    private JCheckBox standaloneInput;
    
    /** 
     * Label for the operation selection combo box. 
     */
    private JLabel operationLabel;
    
    /** 
     * Combo box for selecting the item operation. 
     */
    private JComboBox<String> operationInput;

    /**
     * Constructs a new SetupSpecialItemsPanel.
     */
    public SetupSpecialItemsPanel() {
    }

    @Override
    protected void initInputComponents() {
        super.initInputComponents();

        standaloneLabel = new JLabel("Standalone:");
        standaloneInput = new JCheckBox();

        operationLabel = new JLabel("Operation:");
        operationInput = new JComboBox<>();
    }

    @Override
    protected void setupInputComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        /* Row 1 */

        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        inputPanel.add(inputHeading, gbc);

        /* Row 2 */

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        
        gbc.gridx = 0;
        inputPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        inputPanel.add(nameInput, gbc);

        /* Row 3 */

        gbc.gridy = 2;

        gbc.gridx = 0;
        inputPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(priceInput, gbc);

        /* Row 4 */ 

        gbc.gridy = 3;

        gbc.gridx = 0;
        inputPanel.add(caloriesLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(caloriesInput, gbc);

        /* Row 5 */

        gbc.gridy = 4;

        gbc.gridx = 0;
        inputPanel.add(stockLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(stockInput, gbc);

        /* Row 6 */

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        
        gbc.gridx = 0;
        inputPanel.add(imageChooser, gbc);

        /* Row 7 */

        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        inputPanel.add(standaloneLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(standaloneInput, gbc);

        /* Row 8 */

        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        inputPanel.add(operationLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(operationInput, gbc);

        /* Row 9 */

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        inputPanel.add(setItemButton, gbc);
    
        /* Row 10 */

        gbc.gridy = 9;
        inputPanel.add(removeItemButton, gbc);
    }

    /**
     * Gets the standalone status input value.
     * @return true if the standalone checkbox is selected, false otherwise.
     */
    public boolean getStandaloneInput() {
        return standaloneInput.isSelected();
    }

    /**
     * Sets the standalone status input value.
     * @param isChecked true to check the standalone checkbox, false to uncheck it.
     */
    public void setStandaloneInput(boolean isChecked) {
        standaloneInput.setSelected(isChecked);
    }

    /**
     * Gets the selected operation from the combo box.
     * @return the selected operation as a string.
     */
    public String getOperationInput() {
        return (String) operationInput.getSelectedItem();
    }
    
    /**
     * Sets the selected operation in the combo box based on its index.
     * @param index the index of the operation to select.
     */
    public void setOperationInput(int index) {
        operationInput.setSelectedIndex(index);
    }

    /**
     * Sets the selected operation in the combo box based on its value.
     * @param value the operation value to select.
     */
    public void setOperationInput(String value) {
        // Assume that `value` is in the JComboBox model.
        operationInput.setSelectedItem(value);
    }

    /**
     * Sets the available operations in the operation combo box.
     * @param operations the list of available operations.
     */
    public void setOperations(List<String> operations) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(operations);
        
        operationInput.setModel(model);
        operationInput.setSelectedIndex(0);
    }
}
