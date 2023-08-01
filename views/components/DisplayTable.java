package views.components;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DisplayTable<T, U> extends JScrollPane {
    private JTable table;
    private DefaultTableModel tableModel;

    public DisplayTable(String[] headings) {
        tableModel = new DefaultTableModel();

        for (String heading : headings) {
            tableModel.addColumn(heading);
        }

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

    public void setCell(int colIndex, int rowIndex, Object value) {
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
