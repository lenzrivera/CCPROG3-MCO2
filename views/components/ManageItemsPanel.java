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

public class ManageItemsPanel extends JPanel {
    private int slotCapacity;

    private DisplayTable<Integer, String> slotTable;

    private JPanel inputPanel;
    private JLabel itemImage;

    private JLabel setPriceHeading;
    private JLabel priceLabel;
    private DoubleTextField priceInput;
    private JButton setPriceButton;

    private JLabel setStockHeading;
    private JLabel stockLabel;
    private JLabel quantityLabel;
    private JSpinner quantityInput;
    private JButton addButton;
    private JButton removeButton;

    public ManageItemsPanel() {
        super(new GridLayout(1, 2));
    
        slotCapacity = 0;

        slotTable = new DisplayTable<>("Slot", "Item Name");
        add(slotTable);

        /* Input Panel */

        inputPanel = new JPanel(new GridBagLayout());
        add(inputPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.set(5, 5, 5, 5);

        gbc.gridy = 0;

        itemImage = new JLabel();
        itemImage.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(itemImage, gbc);

        /* Set Price Panel */

        gbc.gridy = 1;
        gbc.gridwidth = 2;

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

    public int getQuantityInput() {
        return (int) quantityInput.getValue();
    }

    public DisplayTable<Integer, String> getSlotTable() {
        return slotTable;
    }

    public double getPriceInput() {
        return (double) priceInput.getValue();
    }

    public void setItemImage(String path) throws IOException {
        Image image = ImageIO.read(new File(path))
                             .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);

        itemImage.setIcon(icon);
    }

    public void setPriceInput(double value) {
        priceInput.setValue(value);
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

    public void setPriceEditListener(ActionListener listener) {
        setPriceButton.addActionListener(listener);
    }
}
