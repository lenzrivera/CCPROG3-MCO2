package model;

/**
 * This class represents the changes made to the stock of an item given
 * several transactions. It is primarily used in conjunction with 
 * the Summary class.
 */
public class ItemSummary {
    /**
     * The stock of the item right after the last (re)stocking.
     */
    private int initialStock;

    /**
     * The quantity sold of the item since the last (re)stocking
     */
    private int stockDiff;

    public ItemSummary(int initialStock) {
        this.initialStock = initialStock;
        stockDiff = 0;
    }

    /**
     * Returns the current stock of the item based on its stock and quantity
     * sold since the last (re)stocking.
     * @return the current stock of the item
     */
    public int getCurrentStock() {
        return initialStock - stockDiff;
    }

    /**
     * Returns the stock of the item right after the last (re)stocking.
     * @return the initial stock of the item
     */
    public int getInitialStock() {
        return initialStock;
    }

    /**
     * Returns the quantity sold of the item since the last (re)stocking.
     * @return the quantity sold of the item since the last (re)stocking.
     */
    public int getStockDiff() {
        return stockDiff;
    }

    /**
     * Sets the quantity sold of the item since the last (re)stocking.
     * @param v the quantity sold of the item
     */
    public void setStockDiff(int v) {
        stockDiff = v;
    }
}
