package views.components;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DenomTable extends JScrollPane {
    private JTable table;
    private DefaultTableModel tableModel;

    public DenomTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Denomination");
        tableModel.addColumn("Quantity");

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        setViewportView(table);
    }

    public void setDenominationCell(int rowNo, double value) {
        if (tableModel.getRowCount() < rowNo) {
            tableModel.addRow(new Object[]{});
        }
    
        tableModel.setValueAt(value, rowNo - 1, 0);
    }

    public void setQuantityCell(int rowNo, int value) {
        if (tableModel.getRowCount() < rowNo) {
            tableModel.addRow(new Object[]{});
        }

        tableModel.setValueAt(value, rowNo - 1, 1);
    }
}
