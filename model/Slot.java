package model;

import java.util.ArrayList;
import java.util.List;

public class Slot {
    public static final int MIN_MAX_CAPACITY = 10;

    protected int capacity;
    protected List<Item> items;

    protected Item sampleItem;
    protected double unitPrice;

    public Slot(int capacity) {
        this.capacity = capacity;
        items = new ArrayList<>();

        sampleItem = null;
        unitPrice = 0.0;
    }

    /* */

    public int getCapacity() {
        return capacity;
    }

    public Item getSampleItem() {
        return sampleItem;
    }

    public int getStock() {
        return items.size();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double v) {
        unitPrice = v;
    }

    /* */

    public void assignToItem(Item item, double price) {
        items.clear();

        this.sampleItem = item;
        this.unitPrice = price;
    }

    public void clearAssignment() {
        assignToItem(null, 0.0);
    }
    
    public Item dispenseItem() {
        if (items.size() == 0) {
            return null;
        }
        
        return items.remove(0);
    }

    public void stockItem(int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(new Item(sampleItem));
        }
    }
}
