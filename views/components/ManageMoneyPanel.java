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

/**
 * This class represents the panel for managing the money stock in a vending 
 * machine. It allows the money stock to be stocked to or collected from. 
 */
public class ManageMoneyPanel extends JPanel {
    /**
     * The table to display denominations and their quantities.
     */
    private DisplayTable denomTable;

    /**
     * The panel containing various input fields for money management.
     */
    private JPanel inputPanel;

    /**
     * The label to display the section heading.
     */
    private JLabel inputHeading;

    /**
     * The label for selecting the denomination.
     */
    private JLabel denomLabel;

    /**
     * The combo box for selecting the denomination.
     */
    private JComboBox<Double> denomInput;

    /**
     * The label for the quantity input.
     */
    private JLabel quantityLabel;

    /**
     * The spinner for inputting the quantity.
     */
    private JSpinner quantityInput;

    /**
     * The button for collecting money of the selected denomination.
     */
    private JButton collectButton;

    /**
     * The button for collecting all money.
     */
    private JButton collectAllButton;

    /**
     * The button for restocking money.
     */
    private JButton stockButton;

    /**
     * Creates a new ManageMoneyPanel.
     */
    public ManageMoneyPanel() {
        super(new GridLayout(1, 2));
        
        /* TABLE */

        denomTable = new DisplayTable(
            new String[] { "Denomination", "Quantity" }
        );
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

    /**
     * Gets the DisplayTable component for denominations and their quantities.
     * @return the DisplayTable for denominations and quantities.
     */
    public DisplayTable getDenomTable() {
        return denomTable;
    }

    /**
     * Sets the denominations available for selection in the combo box.
     * @param denominations the list of denominations available.
     */
    public void setDenominations(List<Double> denominations) {
        DefaultComboBoxModel<Double> model = new DefaultComboBoxModel<>();
        model.addAll(denominations);
        
        denomInput.setModel(model);
        denomInput.setSelectedIndex(0);
    }

    /**
     * Gets the selected denomination from the combo box.
     * @return the selected denomination.
     */
    public double getSelectedDenom() {
        return (double) denomInput.getSelectedItem();
    }

    /**
     * Gets the inputted quantity from the quantity input field.
     * @return the seleced quantity.
     */
    public int getSelectedQuantity() {
        return (int) quantityInput.getValue();
    }

    /* */

    /**
     * Sets the listener for the collect button.
     * @param listener the action listener for the button.
     */
    public void setCollectListener(ActionListener listener) {
        collectButton.addActionListener(listener);
    }

    /**
     * Sets the listener for the collect all button.
     * @param listener the action listener for the button.
     */
    public void setCollectAllListener(ActionListener listener) {
        collectAllButton.addActionListener(listener);
    }

    /**
     * Sets the listener for the stock button.
     * @param listener the action listener for the button.
     */
    public void setStockListener(ActionListener listener) {
        stockButton.addActionListener(listener);
    }
}
