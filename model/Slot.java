package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a slot in a vending machine. Each slot contains
 * a limited number of items and maintains the price per item.
 */
public class Slot {
    /**
     * The minimum item capacity of a slot. 
     */
    public static final int MIN_MAX_CAPACITY = 10;

    /**
     * The item capacity of the slot.
     */
    protected int capacity;

    /**
     * The array of items stored in the slot.
     */
    protected List<Item> items;

    /**
     * The sample of the item associated with the slot, if any.
     */
    protected Item sampleItem;

    /**
     * The unit price of the items in the slot.
     */    
    protected double unitPrice;

    /**
     * This constructor initializes a slot of a certain capacity. The slot
     * capacity falls back to MIN_MAX_CAPACITY should the given value be
     * less than it.
     * @param capacity the capacity of the slot
     */
    public Slot(int capacity) {
        this.capacity = Math.max(MIN_MAX_CAPACITY, capacity);
        items = new ArrayList<>();

        sampleItem = null;
        unitPrice = 0.0;
    }

    /* */

    /**
     * Returns the item capacity of a slot.
     * @return the capacity of the slot
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the sample of the item associated with the slot, if any.
     * @return the sample item assocated with the slot
     */
    public Item getSampleItem() {
        return sampleItem;
    }

    /**
     * Returns the number of items currently in the slot.
     * @return the number of items in the slot.
     */
    public int getStock() {
        return items.size();
    }

    /**
     * Returns the unit price of each item in the slot.
     * @return the price of each item in the slot.
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the unit price of each item in the slot.
     * @param v the unit price to set to
     */
    public void setUnitPrice(double v) {
        unitPrice = v;
    }

    /* */

    /**
     * Assigns the item associated to the slot along with other pertinent
     * information about the item.
     * @param item the item to assign to the slot.
     * @param price the unit price of the item in the slot.
     */
    public void assignToItem(Item item, double price) {
        items.clear();

        this.sampleItem = item;
        this.unitPrice = price;
    }

    /**
     * Clears the slot of any assigned item and associated information and
     * resets it to the default values.
     */
    public void clearAssignment() {
        assignToItem(null, 0.0);
    }
    
    /**
     * Dispenses the last item placed in the slot, if any.
     * @return the dispensed item; null if the slot is empty.
     */
    public Item dispenseItem() {
        if (items.size() == 0) {
            return null;
        }
        
        return items.remove(0);
    }

    /**
     * Stocks the slot with a given quantity of items, each referenced from the
     * sample item associated with the slot.
     * @param quantity the number of items to stock in the slot.
     * @throws IllegalArgumentException if stocking the items would exceed the 
     * slot capacity.
     */
    public void stockItem(int quantity) {
        if (items.size() + quantity > capacity) {
            throw new IllegalArgumentException("Overstock.");
        }
        
        for (int i = 0; i < quantity; i++) {
            items.add(new Item(sampleItem));
        }
    }
}
