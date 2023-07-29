package views.components;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DisplayTable<T, U> extends JScrollPane {
    private JTable table;
    private DefaultTableModel tableModel;

    public DisplayTable(String col0Heading, String col1Heading) {
        tableModel = new DefaultTableModel();
        tableModel.addColumn(col0Heading);
        tableModel.addColumn(col1Heading);

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        setViewportView(table);
    }

    /* */

    public void setCol0(int rowIndex, T value) {
        if (tableModel.getRowCount() <= rowIndex) {
            tableModel.setRowCount(rowIndex);
        }

        tableModel.setValueAt(value, rowIndex, 0);
    }

    public void setCol1(int rowIndex, U value) {
        if (tableModel.getRowCount() <= rowIndex) {
            tableModel.setRowCount(rowIndex);
        }

        tableModel.setValueAt(value, rowIndex, 1);
    }

    /* */
}
