package model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Summary {
    private double totalPayment;
    private Map<String, ItemSummary> stockChanges;

    public Summary(List<? extends Slot> currentSlots) {
        stockChanges = new HashMap<>();
        totalPayment = 0.0;

        reset(currentSlots);        
    }
    
    /* */

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

    public void addTransaction(String itemName, int quantity, double unitPrice) {
        ItemSummary itemSummary = stockChanges.get(itemName);

        if (
            itemSummary == null ||
            itemSummary.getStockDiff() + quantity > itemSummary.getCurrentStock() 
        ) {
            throw new IllegalArgumentException();
        }

        itemSummary.setStockDiff(itemSummary.getStockDiff() + quantity);
        totalPayment += quantity * unitPrice;
    }

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
