package views.components;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the item displayed in the vending machine. It allows
 * the user to see information about the item.
 */
public class ItemDisplay extends JPanel {
    /**
     * The JLabel to display the image of the item or slot.
     */
    protected JLabel imageLabel;

    /**
     * The JPanel to hold the item information.
     */
    protected JPanel infoPanel;

    /**
     * The JLabel to display the name of the item or slot.
     */
    protected JLabel itemNameLabel;

    /**
     * The JLabel to display the calorie count of the item.
     */
    protected JLabel caloriesLabel;

    /**
     * The JLabel to display the price of the item.
     */
    protected JLabel priceLabel;

    /**
     * The JLabel to display the available stock quantity of the item.
     */
    protected JLabel stockLabel;

    /**
     * The JButton to select the item.
     */
    protected JButton selectButton;
    
    /**
     * Creates a new ItemDisplay component to represent an 
     * item or slot in the vending machine. This constructor 
     * initializes a new ItemDisplay component to visually 
     * represent an item or slot in the vending machine.
     * @param itemName      name of the item.
     * @param itemCalories  calories of the item.
     * @param itemPrice     price of the item.
     * @param itemStock     stock of the item.
     * @param imagePath     image path of the item.
     * @throws IOException  If there is an error reading or loading 
     *                      the image from the given imagePath.
     */
    public ItemDisplay(
        String itemName,
        double itemCalories,
        double itemPrice,
        int itemStock,
        String imagePath
    ) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(250, 100));

        try {
            imageLabel = new JLabel(
                new ImageIcon(
                    ImageIO.read(new File(imagePath))
                           .getScaledInstance(100, 100, Image.SCALE_SMOOTH) 
                )
            );
        } catch (IOException e) {
            imageLabel = new JLabel("[image not found]");
        }
        
        add(imageLabel);

        add(Box.createHorizontalStrut(5));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        add(infoPanel);

        itemNameLabel = new JLabel(itemName);
        infoPanel.add(itemNameLabel);

        caloriesLabel = new JLabel(itemCalories + " Calories");
        infoPanel.add(caloriesLabel);

        priceLabel = new JLabel("P" + itemPrice);
        infoPanel.add(priceLabel);

        stockLabel = new JLabel("Stock: " + itemStock);
        infoPanel.add(stockLabel);

        add(Box.createHorizontalGlue());

        selectButton = new JButton("Select");
        add(selectButton);
    }

    /* */

    /**
     * Sets the enable state of the "Select" button.
     * @param enabled A boolean value 
     */
    public void setSelectEnable(boolean enabled) {
        selectButton.setEnabled(enabled);
    }

    /**
     * Sets the stock value to be displayed in the stock label.
     * @param stock The integer value representing the stock quantity to
     * be displayed.
     */
    public void setStockValue(int stock) {
        stockLabel.setText("Stock: " + stock);
    }

    /* */

    /**
     * Sets an ActionListener for the "Select" button.
     * @param listener The ActionListener to be registered for the 
     * "Select" button.
     */
    public void setSelectButtonListener(ActionListener listener) {
        selectButton.addActionListener(listener);
    }
}

