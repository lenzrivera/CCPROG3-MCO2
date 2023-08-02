package views.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class SpecialItemDisplay extends JPanel {
    private Box.Filler filler1;
    private Box.Filler filler2;
    private JLabel imageLabel;
    private JLabel itemLabel;
    private JLabel caloriesLabel;
    private JLabel priceLabel;
    private JLabel stockLabel;
    private JPanel infoPanel;

    private JSpinner itemSpinner;

    public SpecialItemDisplay(String itemName,
                              double itemCalories,
                              double itemPrice,
                              int itemStock,
                              String imagePath
    ) throws IOException {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(250,100));

        imageLabel = new JLabel(new ImageIcon(
                ImageIO.read(new File(imagePath))
                        .getScaledInstance(100,100, Image.SCALE_SMOOTH)
        ));
        add(imageLabel);

        add(Box.createHorizontalStrut(5));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        add(infoPanel);

        itemLabel = new JLabel(itemName);
        infoPanel.add(itemLabel);

        caloriesLabel = new JLabel(itemCalories + " Calories");
        infoPanel.add(caloriesLabel);

        priceLabel = new JLabel("P" + itemPrice);
        infoPanel.add(priceLabel);

        stockLabel = new JLabel();
        infoPanel.add(stockLabel);

        add(Box.createHorizontalGlue());

        itemSpinner = new JSpinner();
        itemSpinner.setMaximumSize(new Dimension(100, 50));
        itemSpinner.setModel(new SpinnerNumberModel(0,0,9,1));
        add(itemSpinner);
    }

    /* */

    public void setStockValue(int stock) {
        stockLabel.setText("Stock: " + stock);
    }

    public void setSpinnerEnabled(boolean enabled) {
        itemSpinner.setEnabled(enabled);
    }

    public int getSpinnerValue() {
        return (int) itemSpinner.getValue();
    }

    /* */

    public void setSpinnerListener(ChangeListener listener) {
        itemSpinner.addChangeListener(listener);
    }
}
