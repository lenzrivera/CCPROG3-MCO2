package views.components;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public abstract class SetupItemsPanel extends JPanel {
    protected int selectedSlotNo;

    protected JScrollPane tableScrollPane;
    protected DefaultTableModel tableModel;
    protected JTable table;

    protected JPanel inputPanel;
    protected JLabel inputHeading;

    protected JLabel nameLabel;
    protected JTextField nameInput;

    protected JLabel priceLabel;
    protected DoubleTextField priceInput;

    protected JLabel caloriesLabel;
    protected DoubleTextField caloriesInput;
    
    protected JLabel stockLabel;
    protected JSpinner stockInput;

    protected ImageFileChooser imageChooser;

    protected JButton setItemButton;
    protected JButton removeItemButton;

    public SetupItemsPanel() {
        super(new GridLayout(1, 2));

        selectedSlotNo = 1;
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Slot");
        tableModel.addColumn("Item Name");

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        tableScrollPane = new JScrollPane(table);
        add(tableScrollPane);
        
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        add(inputPanel);

        initInputComponents();
        setupInputComponents();
    }

    protected abstract void setupInputComponents();

    protected void initInputComponents() {
        inputHeading = new JLabel("<html><u>Edit Item Stock</u></html>");

        nameLabel = new JLabel("Name:");
        nameInput = new JTextField(15);

        priceLabel = new JLabel("Price:");
        priceInput = new DoubleTextField(15);

        caloriesLabel = new JLabel("Calories:");
        caloriesInput = new DoubleTextField(15);

        stockLabel = new JLabel("Stock:");

        SpinnerNumberModel stockInputModel = new SpinnerNumberModel();
        stockInputModel.setMinimum(0);
        stockInputModel.setValue(0);
        stockInput = new JSpinner(stockInputModel);

        imageChooser = new ImageFileChooser("Image:");

        setItemButton = new JButton("Set Item");
        removeItemButton = new JButton("Remove Item");
    }

    /* */

    public double getCaloriesInput() {
        return (double) caloriesInput.getValue();
    }

    public void setCaloriesInput(double value) {
        caloriesInput.setValue(value);
    }

    public String getImagePathInput() {
        return imageChooser.getFilePath();
    }

    public void setImagePathInput(String value) {
        imageChooser.setFilePath(value);
    }

    public String getItemNameInput() {
        return nameInput.getText();
    }

    public void setItemNameInput(String value) {
        nameInput.setText(value);
    }

    public double getPriceInput() {
        return (double) priceInput.getValue();
    }

    public void setPriceInput(double value) {
        priceInput.setValue(value);
    }

    public int getSelectedSlotNo() {
        return selectedSlotNo;
    }

    public int getStockInput() {
        return (int) stockInput.getValue();
    }

    public void setStockInput(int value) {
        stockInput.setValue(value);
    }

    /* */

    public void setItemNameCell(int rowNo, String value) {
        tableModel.setValueAt(value, rowNo - 1, 1);
    }

    public void setMaxStock(int maxStock) {
        ((SpinnerNumberModel) stockInput.getModel()).setMaximum(maxStock);
    }

    public void setSlotCount(int slotCount) {
        tableModel.setRowCount(slotCount);
        
        if (table.getSelectedRow() == -1) {
            int selectedIndex = selectedSlotNo - 1;
            table.setRowSelectionInterval(selectedIndex, selectedIndex);
        }
    }

    public void setSlotNumberCell(int rowNo, int value) {
        tableModel.setValueAt(value, rowNo - 1, 0);
    }

    /* */

    public void setItemAddListener(ActionListener listener) {
        setItemButton.addActionListener(listener);
    }

    public void setItemRemoveListener(ActionListener listener) {
        removeItemButton.addActionListener(listener);
    }

    public void setSlotSelectListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(e -> {
            // Don't handle extra invokations of this event: the mouseup part
            // of the selection, and when no selection is actually made.
            if (e.getValueIsAdjusting() || table.getSelectedRow() == -1) {
                return;
            }

            selectedSlotNo = table.getSelectedRow() + 1;

            listener.valueChanged(e);
        });        
    }
}
