package views.components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ItemQtySelector extends JPanel {
    private JLabel itemName;
    private JSpinner spinner;

    public ItemQtySelector() {
        super(new GridLayout(1, 2));

        itemName = new JLabel("");
        add(itemName);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setMinimum(0);
        spinnerModel.setValue(0);

        spinner = new JSpinner(spinnerModel);
        add(spinner);

        setMaximumSize(new Dimension(
            getMaximumSize().width,getMinimumSize().height));
    }

    public String getItemName() {
        return itemName.getText();
    }

    public void setItemName(String name) {
        itemName.setText(name);
    }

    public int getQuantity() {
        return (int) spinner.getValue();
    }

    public void setQuantity(int quantity) {
        assert quantity >= 0;
        spinner.setValue(quantity);
    }
}
