package views.components;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
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

    public void clearCells() {
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
    }

    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    public void setCol0(int rowIndex, T value) {
        setCell(0, rowIndex, value);

    }

    public void setCol1(int rowIndex, U value) {
        setCell(1, rowIndex, value);
    }

    private void setCell(int colIndex, int rowIndex, Object value) {
        if (tableModel.getRowCount() <= rowIndex) {
            tableModel.setRowCount(rowIndex + 1);
        }

        if (table.getSelectedRow() == -1) {
            table.setRowSelectionInterval(0, 0);
        }

        tableModel.setValueAt(value, rowIndex, colIndex);
    }

    /* */

    public void setRowSelectListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(e -> {
            // Don't handle extra invokations of this event: the mouseup part
            // of the selection, and when no selection is actually made.
            if (e.getValueIsAdjusting() || table.getSelectedRow() == -1) {
                return;
            }

            listener.valueChanged(e);
        });        
    }

}
