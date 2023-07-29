package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class StockItemsPanel extends JPanel {
    private int slotCapacity;

    private DisplayTable<Integer, String> slotTable;

    private JPanel inputPanel;

    private JLabel heading;

    private JLabel stockLabel;

    private JLabel quantityLabel;
    private JSpinner quantityInput;
    
    private JButton addButton;
    private JButton removeButton;

    public StockItemsPanel() {
        super(new GridLayout(1, 2));
    
        slotCapacity = 0;

        slotTable = new DisplayTable<>("Slot", "Item Name");
        add(slotTable);

        inputPanel = new JPanel(new GridBagLayout());
        add(inputPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridwidth = 2;

        heading = new JLabel("<html><u>Stock Items</u></html>");
        inputPanel.add(heading, gbc);

        gbc.gridy = 1;

        stockLabel = new JLabel("Item Stock: ");
        inputPanel.add(stockLabel, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;

        quantityLabel = new JLabel("Quantity: ");
        inputPanel.add(quantityLabel, gbc);

        SpinnerNumberModel qtyInputModel = new SpinnerNumberModel();
        qtyInputModel.setMinimum(1);
        qtyInputModel.setValue(1);
        quantityInput = new JSpinner(qtyInputModel);
        inputPanel.add(quantityInput, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;

        addButton = new JButton("Add Quantity");
        inputPanel.add(addButton, gbc);

        gbc.gridy = 4;

        removeButton = new JButton("Remove Quantity");
        inputPanel.add(removeButton, gbc);
    }

    /* */

    public int getQuantityInput() {
        return (int) quantityInput.getValue();
    }

    public DisplayTable<Integer, String> getSlotTable() {
        return slotTable;
    }

    public void setSlotCapacity(int capacity) {
        slotCapacity = capacity;
    }

    public void setStockLabelText(int stock) {
        stockLabel.setText("Stock: " + stock + " / " + slotCapacity);
    }

    /* */

    public void setItemAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setItemRemoveListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }
}
