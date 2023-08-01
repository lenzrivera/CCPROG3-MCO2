package views.components;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * This abstract class contains common functionality for the item setup panels.
 * It provides a table to display item slots and various input components to 
 * edit item information.
 */
public abstract class SetupItemsPanel extends JPanel {
    /**
     * The table to display item slots.
     */
    protected DisplayTable slotTable;

    /**
     * The input panel to edit item information.
     */
    protected JPanel inputPanel;

    /**
     * The label for the input panel heading.
     */
    protected JLabel inputHeading;

    /**
     * The label for the item name input field.
     */
    protected JLabel nameLabel;

    /**
     * The text field for entering the item name.
     */
    protected JTextField nameInput;

    /**
     * The label for the item price input field.
     */
    protected JLabel priceLabel;

    /**
     * The text field for entering the item price.
     */
    protected DoubleTextField priceInput;

    /**
     * The label for the item calories input field.
     */
    protected JLabel caloriesLabel;

    /**
     * The text field for entering the item calories.
     */
    protected DoubleTextField caloriesInput;
    
    /**
     * The label for the item stock input field.
     */
    protected JLabel stockLabel;

    /**
     * The spinner for selecting the item stock quantity.
     */
    protected JSpinner stockInput;

    /**
     * The image file chooser for selecting the item image.
     */
    protected ImageFileChooser imageChooser;

    /**
     * The button to set the item in the slot.
     */
    protected JButton setItemButton;

    /**
     * The button to remove the item from the slot.
     */
    protected JButton removeItemButton;

    /**
     * Constructs a new SetupItemsPanel and associated GUI components.
     */
    public SetupItemsPanel() {
        super(new GridLayout(1, 2));

        slotTable = new DisplayTable(new String[] { "Slot", "Item Name" });
        add(slotTable);
        
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        add(inputPanel);

        initInputComponents();
        setupInputComponents();
    }

    /**
     * Sets up the layout of specific input components for editing item 
     * information.
     */
    protected abstract void setupInputComponents();

    /**
     * Initializes the input components for editing item information.
     */
    protected void initInputComponents() {
        inputHeading = new JLabel("<html><u>Edit Item Stock</u></html>");

        nameLabel = new JLabel("Name:");
        nameInput = new JTextField(15);

        priceLabel = new JLabel("Price:");
        priceInput = new DoubleTextField(15);

        caloriesLabel = new JLabel("Calories:");
        caloriesInput = new DoubleTextField(15);

        stockLabel = new JLabel("Stock:");

        SpinnerNumberModel stockInputModel = new SpinnerNumberModel();
        stockInputModel.setMinimum(0);
        stockInputModel.setValue(0);
        stockInput = new JSpinner(stockInputModel);

        imageChooser = new ImageFileChooser("Image:");

        setItemButton = new JButton("Set Item");
        removeItemButton = new JButton("Remove Item");
    }

    /* */

    /**
     * Gets the value of the calories input field.
     * @return the inputted value
     */
    public double getCaloriesInput() {
        return (double) caloriesInput.getValue();
    }

    /**
     * Sets the value of the calories input field.
     * @param value the value to set
     */
    public void setCaloriesInput(double value) {
        caloriesInput.setValue(value);
    }

    /**
     * Gets the stored full image path in the image chooser.
     * @return the stored full image path
     */
    public String getImagePathInput() {
        return imageChooser.getFilePath();
    }

    /**
     * Sets the value of the full image path in the image chooser.
     * @param value the full image path
     */
    public void setImagePathInput(String value) {
        imageChooser.setFilePath(value);
    }

    /**
     * Gets the value in the item name input field.
     * @return the inputted value
     */
    public String getItemNameInput() {
        return nameInput.getText();
    }

    /**
     * Sets the value in the item name input field.
     * @param value the value to set
     */
    public void setItemNameInput(String value) {
        nameInput.setText(value);
    }

    /**
     * Gets the item price input from the item price field.
     * @return the inputted value
     */
    public double getPriceInput() {
        return (double) priceInput.getValue();
    }

    /**
     * Sets the value in the item price field.
     * @param value the value to set
     */
    public void setPriceInput(double value) {
        priceInput.setValue(value);
    }

    /**
     * Gets the number (index + 1) of the selected slot in the slot table.
     * @return the index of the selected slot
     */
    public int getSelectedSlotNo() {
        return slotTable.getSelectedRowIndex() + 1;
    }

    /**
     * Gets the entered value of the item stock spinner.
     * @return the item stock input
     */
    public int getStockInput() {
        return (int) stockInput.getValue();
    }

    /**
     * Sets the item stock input to the specified value.
     * @param value the value to set
     */
    public void setStockInput(int value) {
        stockInput.setValue(value);
    }

    /* */

    /**
     * Gets the DisplayTable instance for displaying the slots.
     * @return the DisplayTable instance
     */
    public DisplayTable getSlotTable() {
        return slotTable;
    }

    /**
     * Sets the maximum stock that an item can have in the machine.
     * @param maxStock the maximum stock
     */
    public void setMaxStock(int maxStock) {
        ((SpinnerNumberModel) stockInput.getModel()).setMaximum(maxStock);
    }

    /* */

    /**
     * Sets the listener for the set item button.
     * @param listener the action listener for the button.
     */
    public void setItemSetListener(ActionListener listener) {
        setItemButton.addActionListener(listener);
    }

    /**
     * Sets the listener for the remove item button.
     * @param listener the action listener for the button.
     */
    public void setItemRemoveListener(ActionListener listener) {
        removeItemButton.addActionListener(listener);
    }
}
