package views.components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * This class represents a componenting for selecting the quantity of an item.
 * It consists of a label to display the item name and a spinner to input the 
 * quantity.
 */
public class ItemQtySelector extends JPanel {
    /**
     * The name of the item whose quantity is being selected
     */
    private JLabel itemName;

    /**
     * The spinner that allows a quantity to be entered
     */
    private JSpinner spinner;

    /**
     * Creates an ItemQtySelector with an empty item name and initial quantity
     * set to zero.
     */
    public ItemQtySelector() {
        super(new GridLayout(1, 2));

        itemName = new JLabel("");
        add(itemName);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setMinimum(0);
        spinnerModel.setValue(0);

        spinner = new JSpinner(spinnerModel);
        add(spinner);

        setMaximumSize(new Dimension(
            getMaximumSize().width, getMinimumSize().height));
    }

    /**
     * Returns the name of the item associated with this selector.
     * @return the name of the item.
     */
    public String getItemName() {
        return itemName.getText();
    }

    /**
     * Sets the name of the item associated with this selector.
     * @param name the name of the item.
     */
    public void setItemName(String name) {
        itemName.setText(name);
    }

    /**
     * Returns the selected quantity of the item.
     * @return the selected quantity.
     */
    public int getQuantity() {
        return (int) spinner.getValue();
    }

    /**
     * Sets the quantity of the item. The quantity must be non-negative (>= 0).
     * @param quantity the quantity to set.
     * @throws IllegalArgumentException if the quantity is negative.
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        spinner.setValue(quantity);
    }
}
