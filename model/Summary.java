package model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Summary<T extends Slot> {
    
    private HashMap<String, ItemSummary> stockChanges;
    
    private double totalPayments;    

    public Summary(List<T> currentSlots) {
        stockChanges = new HashMap<>();
        totalPayments = 0.0;

        reset(currentSlots);        
    }
    
    public Set<Map.Entry<String, ItemSummary>> getStockChanges() {
        return stockChanges.entrySet();
    }

    /**
     * Returns the total payments received by a vending machine, flattened to
     * a single double value.
     * @return the total payments received by a vending machine
     */
    public double getTotalPayments() {
        return totalPayments;
    }

    public void addTransaction(String itemName, int quantity, double unitPrice) {
        ItemSummary itemSummary = stockChanges.get(itemName);

        if (itemSummary == null) {
            return;
        }

        itemSummary.setStockDiff(itemSummary.getStockDiff() + quantity);
        totalPayments += quantity * unitPrice;
    }

    public void reset(List<T> currentSlots) {
        totalPayments = 0;
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
