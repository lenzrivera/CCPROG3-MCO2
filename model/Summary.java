package model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a summary of the transactions made in a vending 
 * machine, containing the quantity sold of each item as well as the total
 * payments received.
 */
public class Summary {
    /**
     * The total amount of payments received by a vending machine.
     */
    private double totalPayment;

    /**
     * A map of items (represented by their names) to their ItemSummary entries
     * containing the relevant data.
     */
    private Map<String, ItemSummary> stockChanges;

    /**
     * This constructor initializes a Summary from the contents of the slots
     * of a vending machine, priming transaction summaries of such slots to
     * be recorded.
     * @param currentSlots the current slots of the vending machine, used to
     * determine the items whose summaries must be stored
     */
    public Summary(List<? extends Slot> currentSlots) {
        stockChanges = new HashMap<>();
        totalPayment = 0.0;

        reset(currentSlots);        
    }
    
    /* */

    /**
     * Returns the map associating each item to its changes occured from 
     * transactions since the last restock.
     * @return the map containing the changes for each item
     */
    public Map<String, ItemSummary> getStockChanges() {
        return stockChanges;
    }

    /**
     * Returns the total payments received by a vending machine, flattened to
     * a single double value.
     * @return the total payments received by a vending machine
     */
    public double getTotalPayment() {
        return totalPayment;
    }

    /* */

    /**
     * Records a transaction that occured in a vending machine since its last 
     * restock for a particular item. 
     * @param itemName the name of transacted item
     * @param quantity the quantity of the item transacted
     * @param unitPrice the unit price of the transacted item
     */
    public void addTransaction(String itemName, int quantity, double unitPrice) {
        ItemSummary itemSummary = stockChanges.get(itemName);

        if (
            itemSummary == null ||
            itemSummary.getStockDiff() + quantity > itemSummary.getInitialStock() 
        ) {
            throw new IllegalArgumentException();
        }

        itemSummary.setStockDiff(itemSummary.getStockDiff() + quantity);
        totalPayment += quantity * unitPrice;
    }

    /**
     * Resets the summary entries to the state of a newly restocked vending 
     * machine. 
     * @param currentSlots the current slot contents of a newly restocked 
     * vending machine
     */
    public void reset(List<? extends Slot> currentSlots) {
        totalPayment = 0;
        stockChanges.clear();
        
        for (Slot slot : currentSlots) {
            if (slot.getStock() == 0) {
                continue;
            }

            String itemName = slot.getSampleItem().getName();
            ItemSummary initialSummary = new ItemSummary(slot.getStock());
        
            stockChanges.put(itemName, initialSummary);
        }
    }
}
