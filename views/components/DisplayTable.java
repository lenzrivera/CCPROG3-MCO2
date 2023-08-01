package views.components;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * This class represents a custom scrollable table that can be updated 
 * on-the-fly.
 */
public class DisplayTable extends JScrollPane {
    /**
     * The actual table
     */
    private JTable table;

    /**
     * The table model used to display data onto the table
     */
    private DefaultTableModel tableModel;

    /**
     * Constructs a DisplayTable with the specified headings for each column.
     * @param headings An array of strings representing the column headings.
     */
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

    /**
     * Clears all cells in the table.
     */
    public void clearCells() {
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
    }

    /**
     * Gets the index of the currently selected row in the table.
     * @return The index of the selected row, or -1 if no row is selected.
     */
    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    /**
     * Sets the value of a specific cell in the table. If the row index exceeds
     * the current row count, additional rows are added to accommodate the new 
     * value. If no row is selected in the table, the first row will be selected.
     *
     * @param colIndex the column index of the cell.
     * @param rowIndex the row index of the cell.
     * @param value the value to set in the cell.
     */
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

    /**
     * Sets a listener to be notified when a row is selected in the table.
     * @param listener the listener to call on selection
     */
    public void setRowSelectListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(e -> {
            // Don't handle extra invocations of this event: the mouseup part
            // of the selection, and when no selection is actually made.
            if (e.getValueIsAdjusting() || table.getSelectedRow() == -1) {
                return;
            }

            listener.valueChanged(e);
        });
    }
}
