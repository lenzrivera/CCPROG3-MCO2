package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

/**
 * This class represents the panel for managing vending machine items. It
 * allows for the setting the price and stock of each item in the machine.
 */
public class ManageItemsPanel extends JPanel {
    /**
     * The table to display slot number and item name per slot.
     */
    private DisplayTable slotTable;

    /**
     * The panel containing various input fields for item management.
     */
    private JPanel inputPanel;

    /**
     * The label to display the item image.
     */
    private JLabel itemImage;

    /**
     * The label for setting the item price.
     */
    private JLabel setPriceHeading;

    /**
     * The label for the item price.
     */
    private JLabel priceLabel;

    /**
     * The text field for setting the item price.
     */
    private DoubleTextField priceInput;

    /**
     * The button to set the item price.
     */
    private JButton setPriceButton;

    /**
     * The label for managing item stock.
     */
    private JLabel setStockHeading;

    /**
     * The label to display the current item stock.
     */
    private JLabel stockLabel;

    /**
     * The label for the item quantity input.
     */
    private JLabel quantityLabel;

    /**
     * The spinner to input the quantity of items to add or remove.
     */
    private JSpinner quantityInput;

    /**
     * The button to add item quantity.
     */
    private JButton addButton;

    /**
     * The button to remove item quantity.
     */
    private JButton removeButton;

    /**
     * Creates a new ManageItemsPanel.
     */
    public ManageItemsPanel() {
        super(new GridLayout(1, 2));

        slotTable = new DisplayTable(new String[] { "Slot", "Item Name" });
        add(slotTable);

        /* Input Panel */

        inputPanel = new JPanel(new GridBagLayout());
        add(inputPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets.set(5, 5, 15, 5);

        itemImage = new JLabel();
        itemImage.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(itemImage, gbc);

        /* Set Price Panel */

        gbc.gridy = 1;
        gbc.insets.set(5, 5, 5, 5);

        setPriceHeading = new JLabel("<html><u>Set Item Price</u></html>");
        inputPanel.add(setPriceHeading, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;

        priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        inputPanel.add(priceLabel, gbc);
        
        priceInput = new DoubleTextField(15);
        gbc.gridx = 1;
        inputPanel.add(priceInput, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;

        setPriceButton = new JButton("Set Price");
        gbc.gridx = 0;
        gbc.insets.set(5, 5, 15, 5);
        inputPanel.add(setPriceButton, gbc);

        /* Set Stock Panel */

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets.set(5, 5, 5, 5);

        setStockHeading = new JLabel("<html><u>Stock Items</u></html>");
        inputPanel.add(setStockHeading, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;

        stockLabel = new JLabel("Item Stock: ");
        inputPanel.add(stockLabel, gbc);

        gbc.gridy = 6;
        gbc.gridwidth = 1;

        quantityLabel = new JLabel("Quantity: ");
        gbc.gridx = 0;
        inputPanel.add(quantityLabel, gbc);

        SpinnerNumberModel qtyInputModel = new SpinnerNumberModel();
        qtyInputModel.setMinimum(1);
        qtyInputModel.setValue(1);
        gbc.gridx = 1;
        quantityInput = new JSpinner(qtyInputModel);
        inputPanel.add(quantityInput, gbc);

        gbc.gridy = 7;
        gbc.gridwidth = 2;

        addButton = new JButton("Add Quantity");
        gbc.gridx = 0;
        inputPanel.add(addButton, gbc);

        gbc.gridy = 8;

        removeButton = new JButton("Remove Quantity");
        inputPanel.add(removeButton, gbc);
    }

    /* */

    /**
     * Gets the quantity input value from the spinner.
     * @return The quantity input value.
     */
    public int getQuantityInput() {
        return (int) quantityInput.getValue();
    }

    /**
     * Gets the price input value from the text field.
     * @return the price input value.
     */
    public double getPriceInput() {
        return (double) priceInput.getValue();
    }

    /**
     * Gets the selected row index in the slot table.
     * @return the selected row index
     */
    public int getSelectedRowIndex() {
        return slotTable.getSelectedRowIndex();
    }

    /**
     * Sets the item image to display in the panel.
     * @param path the file path of the item image.
     * @throws IOException If there is an error reading the image file.
     */
    public void setItemImage(String path) throws IOException {
        Image image = ImageIO.read(new File(path))
                             .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);

        itemImage.setIcon(icon);
    }

    /**
     * Sets the price input value in the text field.
     * @param value the price value to set.
     */
    public void setPriceInput(double value) {
        priceInput.setValue(value);
    }

    /**
     * Sets the label text for the item stock quantity.
     * @param stock the current item stock quantity.
     * @param maxCapacity the maximum allowed capacity in 
     *                    the slot.
     */
    public void setStockLabelText(int stock, int maxCapacity) {
        stockLabel.setText("Stock: " + stock + " / " + maxCapacity);
    }

    /**
     * Sets the slot number value for a cell in the slots column.
     * @param rowIndex the row index of the cell
     * @param value the slot number
     */
    public void setSlotNoCell(int rowIndex, int value) {
        slotTable.setCell(0, rowIndex, value);
    }

    /**
     * Sets the item name value for a cell in the items column.
     * @param rowIndex the row index of the cell
     * @param value the item name
     */
    public void setItemNameCell(int rowIndex, String value) {
        slotTable.setCell(1, rowIndex, value);  
    }

    /* */

    /**
     * Adds a listener for the add quantity button.
     * @param listener the action listener for the button.
     */
    public void setItemAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the remove quantity button.
     * @param listener The action listener for the button.
     */
    public void setItemRemoveListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    /**
     * Adds a listener for the set price button.
     * @param listener The listener for the button.
     */
    public void setPriceEditListener(ActionListener listener) {
        setPriceButton.addActionListener(listener);
    }

    /**
     * Adds a listener for row selection in the slot table.
     * @param listener the listener for the table
     */
    public void setRowSelectListener(ListSelectionListener listener) {
        slotTable.setRowSelectListener(listener);
    }
}
