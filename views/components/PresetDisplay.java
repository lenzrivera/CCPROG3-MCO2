package views.components;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


/**
 * The PresetDisplay class represents a custom JPanel component 
 * that displays information about a preset in the vending machine.
 */
public class PresetDisplay extends JPanel {
    /**
     * The button used to select or deselect the item.
     */
    private JButton selectButton;

    /**
     * The label displaying the image of the item.
     */
    private JLabel imageLabel;

    /**
     * The label displaying the name of the item.
     */
    private JLabel itemNameLabel;

    /**
     * The label displaying the number of calories in the item.
     */
    private JLabel caloriesLabel;

    /**
     * The label displaying the price of the item.
     */
    private JLabel priceLabel;

    /**
     * The main panel that contains the components representing the 
     * item display.
     */
    private JPanel mainPanel;

    /**
     * Constructs a new PresetDisplay for displaying information about 
     * a preset in the vending machine user interface.
     * @param itemName     name of the item.
     * @param itemCalories calories of the item.
     * @param itemPrice    price of the item.
     * @param imagePath    image path of the item.
     */
    public PresetDisplay(
        String itemName,
        double itemCalories,
        double itemPrice,
        String imagePath
    ) {
        mainPanel = new JPanel();
        itemNameLabel = new JLabel();
        caloriesLabel = new JLabel();
        priceLabel = new JLabel();
        selectButton = new JButton();
        
        setPreferredSize(new Dimension(250, 100));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

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

        add(new Box.Filler(new Dimension(5, 0),
                new Dimension(5, 0),
                new Dimension(5, 32767)));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        itemNameLabel.setText(itemName);
        mainPanel.add(itemNameLabel);

        caloriesLabel.setText(itemCalories + " Calories");
        mainPanel.add(caloriesLabel);

        priceLabel.setText("P" + itemPrice);
        mainPanel.add(priceLabel);

        add(mainPanel);
        add(new Box.Filler(new Dimension(0, 0),
                new Dimension(0, 0),
                new Dimension(32767, 0)));

        selectButton.setText("Select");
        add(selectButton);
    }


    /**
     * Checks if the item can be selected.
     * @return true if the item can be selected (select button 
     *         text is "Select"), false otherwise.
     */
    public boolean canBeSelected() {
        return selectButton.getText().equalsIgnoreCase("Select");
    }


    /**
     * Retrieves the select button associated with the item display.
     * @return The JButton instance representing the select button 
     *         associated with the item display.
     */
    public JButton getSelectButton() {
        return selectButton;
    }

    /**
     * Sets the enable status of the select button.
     * @param b true to enable the select button, 
     *                false to disable it.
     */
    public void setSelectEnable(boolean b) {
        selectButton.setEnabled(b);
    }

    /**
     * Sets the state of the select button based on the item's 
     * deselection status.
     * @param isDeselected true if the item is deselected, 
     *                     false otherwise.
     */
    public void setSelectState(boolean isDeselected) {
        if (isDeselected) {
            selectButton.setText("Deselect");
        } else {
            selectButton.setText("Select");
        }
    }


    /**
     * Sets the highlight show status of the item display.
     * @param b true, to show the highlight (change background 
     *          color to yellow), false, to remove the highlight 
     *          (use default background color).
     */
    public void setHighlightShow(boolean b) {
        if (b) {
            setBackground(Color.YELLOW);
        } else {
            setBackground(UIManager.getColor("Panel.background"));
        }
    }

    /* */


    /**
     * Sets an ActionListener for the select button in the vending 
     * machine user interface.
     * @param listener The ActionListener to be set for the select button.
     */
    public void setSelectButtonListener(ActionListener listener) {
        selectButton.addActionListener(listener);
    }
}

