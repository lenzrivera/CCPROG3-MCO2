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

public class ManageMoneyPanel extends JPanel {
    private DenomTable denomTable;

    private JPanel inputPanel;
    private JLabel inputHeading;

    private JLabel denomLabel;
    private JComboBox<Double> denomInput;

    private JLabel quantityLabel;
    private JSpinner quantityInput;

    private JButton collectButton;
    private JButton collectAllButton;
    private JButton stockButton;

    public interface CollectAllListener {
        public void run();
    }

    public interface DenomSelectListener {
        public void run(double denom, int quantity);
    }

    public ManageMoneyPanel() {
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

        inputHeading = new JLabel("<html><u>Manage Money</u></html>");
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
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        collectButton = new JButton("Collect");
        inputPanel.add(collectButton, gbc);
        
        gbc.gridy = 4;

        collectAllButton = new JButton("Collect All");
        inputPanel.add(collectAllButton, gbc);

        gbc.gridy = 5;

        stockButton = new JButton("Stock");
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

    public int getSelectedValue() {
        return (int) quantityInput.getValue();
    }

    /* */

    public void setCollectListener(ActionListener listener) {
        collectButton.addActionListener(listener);
    }

    public void setCollectAllListener(ActionListener listener) {
        collectAllButton.addActionListener(listener);
    }

    public void setStockListener(ActionListener listener) {
        stockButton.addActionListener(listener);
    }
}
