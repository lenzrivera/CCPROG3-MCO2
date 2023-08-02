package views.components;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ItemDisplay extends JPanel {
    private JLabel imageLabel;

    private JPanel infoPanel;
    private JLabel itemNameLabel;
    private JLabel caloriesLabel;
    private JLabel priceLabel;
    private JLabel stockLabel;
    
    private JButton selectButton;
    
    public ItemDisplay(
        String itemName,
        double itemCalories,
        double itemPrice,
        int itemStock,
        String imagePath
    ) throws IOException {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(250, 100));

        imageLabel = new JLabel(new ImageIcon(
            ImageIO.read(new File(imagePath))
                   .getScaledInstance(100, 100, Image.SCALE_SMOOTH) 
        ));
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

    public void setButtonEnabled(boolean enabled) {
        selectButton.setEnabled(enabled);
    }

    public void setStockValue(int stock) {
        stockLabel.setText("Stock: " + stock);
    }

    /* */

    public void setSelectButtonListener(ActionListener listener) {
        selectButton.addActionListener(listener);
    }
}

