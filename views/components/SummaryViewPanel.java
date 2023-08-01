package views.components;

import java.awt.Font;

import javax.swing.*;

/**
 * A panel for displaying a summary view of stock and income information.
 */
public class SummaryViewPanel extends JPanel {
    /** 
     * The table displaying stock and quantity sold information. 
     */
    private DisplayTable stockTable;

    /** 
     * The label displaying the total income. 
     */
    private JLabel incomeLabel;

    /**
     * Constructs a new SummaryViewPanel.
     */
    public SummaryViewPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        stockTable = new DisplayTable(new String[] { 
            "Item Name",
            "Last Stock", 
            "Curr Stock", 
            "Qty Sold" 
        });
        stockTable.setAlignmentX(LEFT_ALIGNMENT);
        add(stockTable);

        incomeLabel = new JLabel();
        incomeLabel.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        incomeLabel.setText("Total Income: P" + 0.0);
        add(incomeLabel);
    }

    /**
     * Sets the name cell of the stock table at the specified row index.
     * @param rowIndex The row index of the cell to set.
     * @param name The item name to set in the cell.
     */
    public void setNameCell(int rowIndex, String name) {
        stockTable.setCell(0, rowIndex, name);
    }

    /**
     * Sets the last stock cell of the stock table at the specified row index.
     * @param rowIndex The row index of the cell to set.
     * @param value The last stock value to set in the cell.
     */
    public void setLastStockCell(int rowIndex, int value) {
        stockTable.setCell(1, rowIndex, value);
    }

    /**
     * Sets the current stock cell of the stock table at the specified row index.
     * @param rowIndex The row index of the cell to set.
     * @param value The current stock value to set in the cell.
     */
    public void setCurrStockCell(int rowIndex, int value) {
        stockTable.setCell(2, rowIndex, value);
    }

    /**
     * Sets the quantity sold cell of the stock table at the specified row index.
     * @param rowIndex The row index of the cell to set.
     * @param value The quantity sold value to set in the cell.
     */
    public void setQtyCell(int rowIndex, int value) {
        stockTable.setCell(3, rowIndex, value);
    }

    /**
     * Updates the total income displayed in the income label.
     * @param credit The new total income value to display.
     */
    public void updateTotalIncome(double credit) {
        incomeLabel.setText("Total Income: P" + credit);
    }
}
