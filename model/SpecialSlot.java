package model;

import java.util.ArrayList;
import java.util.List;

public class SpecialSlot extends Slot {
    private ArrayList<ItemOperation> itemOperations;

    private boolean standalone;

    public SpecialSlot(int capacity) {
        super(capacity);
    }

    public List<ItemOperation> getItemOperations() {
        return itemOperations;
    }

    public boolean isStandalone() {
        return standalone;
    }

    @Override
    public void assignToItem(String name, double price, double calories) {
        assignToItem(name, price, calories, true, null);
    }

    public void assignToItem(
        String name,
        double price,
        double calories,
        boolean standalone,
        List<ItemOperation> operations
    ) {
        assignToItem(name, price, calories);

        this.standalone = standalone;
        this.itemOperations = new ArrayList<>(operations);
    }
}
