package views.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class StockChangePanel extends JPanel {
    private JScrollPane tableScrollPane;
    private JTable table;
    private DefaultTableModel tableModel;

    private JPanel inputPanel;
    private JLabel inputHeading;
    private JLabel denomLabel;
    private JComboBox<Double> denomInput;
    private JLabel quantityLabel;
    private JSpinner quantityInput;
    private JButton stockButton;
    private JButton nextButton;

    public interface AddDenominationListener {
        public void run(double denom, int quantity);
    }

    public interface NextButtonListener {
        public void run();
    }

    public StockChangePanel() {
        super(new GridLayout(1, 2));

        /* TABLE */

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Denomination");
        tableModel.addColumn("Quantity");

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        tableScrollPane = new JScrollPane(table);
        add(tableScrollPane);

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

        gbc.gridy = 4;

        nextButton = new JButton("Next");
        inputPanel.add(nextButton, gbc);
    }

    public void setAddDenominationListener(AddDenominationListener listener) {
        stockButton.addActionListener(e -> {
            listener.run(
                (double) denomInput.getSelectedItem(), 
                (int) quantityInput.getValue()
            );
        });
    }

    public void setDenominationCell(int rowNo, double value) {
        tableModel.setValueAt(value, rowNo - 1, 0);
    }

    public void setDenominations(List<Double> denominations) {
        DefaultComboBoxModel<Double> model = new DefaultComboBoxModel<>();
        model.addAll(denominations);
        
        denomInput.setModel(model);
        denomInput.setSelectedIndex(0);
    }

    public void setNextButtonListener(NextButtonListener listener) {
        nextButton.addActionListener(e -> {
            listener.run();
        });
    }

    public void setQuantityCell(int rowNo, int value) {
        tableModel.setValueAt(value, rowNo - 1, 1);
    }
}
