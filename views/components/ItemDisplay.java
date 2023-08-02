package views.components;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ItemDisplay extends JPanel {
    private JPanel mainPanel;

    private JLabel imageLabel;

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
        imageLabel = new JLabel();

        mainPanel = new JPanel();
        itemNameLabel = new JLabel();
        caloriesLabel = new JLabel();
        priceLabel = new JLabel();
        stockLabel = new JLabel();

        selectButton = new JButton();

        setPreferredSize(new Dimension(250, 100));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        Image image = ImageIO.read(new File(imagePath))
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);

        imageLabel.setIcon(icon);
        imageLabel.setMaximumSize(new Dimension(100, 100));
        imageLabel.setMinimumSize(new Dimension(100, 100));
        imageLabel.setPreferredSize(new Dimension(100, 100));
        
        add(imageLabel);
        add(
            new Box.Filler(
                new Dimension(5, 0),
                new Dimension(5, 0),
                new Dimension(5, 32767)
            )
        );

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        itemNameLabel.setText(itemName);
        mainPanel.add(itemNameLabel);

        caloriesLabel.setText(itemCalories + " Calories");
        mainPanel.add(caloriesLabel);

        priceLabel.setText("Php " + itemPrice);
        mainPanel.add(priceLabel);

        stockLabel.setText("Stock: " + itemStock);
        mainPanel.add(stockLabel);

        add(mainPanel);
        add(
            new Box.Filler(
                new Dimension(0, 0),
                new Dimension(0, 0),
                new Dimension(32767, 0)
            )
        );

        selectButton.setText("Select");
        add(selectButton);
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public void setButtonEnabled(boolean enabled) {
        selectButton.setEnabled(enabled);
    }

    public void setSelectButtonListener(ActionListener listener) {
        selectButton.addActionListener(listener);
    }
}

