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
    private JPanel mainPanel;
    private JSpinner itemSpinner;
    private String name;
    private double calories;
    private double price;
    private int stock;
    private String image;
    public SpecialItemDisplay(String itemName,
                              double itemCalories,
                              double itemPrice,
                              int itemStock
    ) {
        this.name = itemName;
        this.calories = itemCalories;
        this.price = itemPrice;
        this.stock = itemStock;

        imageLabel = new JLabel();
        filler2 = new Box.Filler(new Dimension(5, 0), new Dimension(5, 0), new Dimension(5, 32767));
        mainPanel = new JPanel();
        itemLabel = new JLabel();
        caloriesLabel = new JLabel();
        priceLabel = new JLabel();
        stockLabel = new JLabel();
        filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        itemSpinner = new JSpinner();

        setPreferredSize(new Dimension(300, 100));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        imageLabel.setMaximumSize(new Dimension(100, 100));
        imageLabel.setMinimumSize(new Dimension(100, 100));
        imageLabel.setPreferredSize(new Dimension(100, 100));
        add(imageLabel);
        add(filler2);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        itemLabel.setText(itemName);
        mainPanel.add(itemLabel);

        caloriesLabel.setText(itemCalories + " Calories");
        mainPanel.add(caloriesLabel);

        priceLabel.setText("Php " + itemPrice);
        mainPanel.add(priceLabel);

        stockLabel.setText("Stock: " + itemStock);
        mainPanel.add(stockLabel);

        add(mainPanel);
        add(filler1);

        itemSpinner.setMaximumSize(new Dimension(32767, 100));
        itemSpinner.setModel(new SpinnerNumberModel(0,0,9,1));
        add(itemSpinner);
    }

    public void setImageIcon(String imagePath) throws IOException {
        Image image = ImageIO.read(new File(imagePath))
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);

        imageLabel.setIcon(icon);
    }

    public void setSpinnerListener(ChangeListener listener) {
        itemSpinner.addChangeListener(listener);
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

    public JSpinner getSpinner() {
        return itemSpinner;
    }
}
