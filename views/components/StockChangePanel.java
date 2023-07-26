package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class StockChangePanel extends JPanel {
    private DenomTable denomTable;

    private JPanel inputPanel;
    private JLabel inputHeading;

    private JLabel denomLabel;
    private JComboBox<Double> denomInput;

    private JLabel quantityLabel;
    private JSpinner quantityInput;

    private JButton stockButton;

    public StockChangePanel() {
        super(new GridLayout(1, 2));

        /* TABLE */

        denomTable = new DenomTable();
        add(denomTable);

        /* INPUT PANEL */

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        add(inputPanel);

        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;

        inputHeading = new JLabel("<html><u>Stock Change</u></html>");
        gbc.gridx = 0;
        gbc.gridwidth = 2;   
        inputPanel.add(inputHeading, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;   

        denomLabel = new JLabel("Denomination:");
        gbc.gridx = 0;
        inputPanel.add(denomLabel, gbc);

        denomInput = new JComboBox<>();
        gbc.gridx = 1;
        inputPanel.add(denomInput, gbc);

        gbc.gridy = 2;

        quantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        inputPanel.add(quantityLabel,gbc);

        SpinnerNumberModel qtyInputModel = new SpinnerNumberModel();
        qtyInputModel.setMinimum(1);  
        qtyInputModel.setValue(1);

        quantityInput = new JSpinner(qtyInputModel);
        gbc.gridx = 1;
        inputPanel.add(quantityInput, gbc);

        gbc.gridy = 3;
        
        stockButton = new JButton("Stock");
        gbc.gridx = 0;
        gbc.gridwidth = 2;  
        inputPanel.add(stockButton, gbc);
    }

    /* */

    public DenomTable getDenomTable() {
        return denomTable;
    }

    public void setDenominations(List<Double> denominations) {
        DefaultComboBoxModel<Double> model = new DefaultComboBoxModel<>();
        model.addAll(denominations);
        
        denomInput.setModel(model);
        denomInput.setSelectedIndex(0);
    }

    public double getSelectedDenom() {
        return (double) denomInput.getSelectedItem();
    }

    public int getSelectedQuantity() {
        return (int) quantityInput.getValue();
    }

    /* */

    public void setAddDenominationListener(ActionListener listener) {
        stockButton.addActionListener(listener);
    }
}
