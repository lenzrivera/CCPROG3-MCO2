package model;

/**
 * Represents the slots used in a special vending machine, hence it inherits
 * the regular Slot class. Apart from regular slot features, it also indicates
 * the relation of its associated item with other items for preset orders.
 */
public class SpecialSlot extends Slot {
    /**
     * Whether the slot contains a base item
     */
    private boolean base;

    /**
     * Whether the slot contains a standalone item or not
     */
    private boolean standalone;

    /**
     * The operation to be performed on the items in the slot
     */
    private Operation operation;

    /**
     * Constructs a new SpecialSlot instance with the specified capacity.
     * All special slots intially assume their items to be a standalone
     * non-base item that is only generally prepared. The slot capacity falls 
     * back to MIN_MAX_CAPACITY should the given value be less than it.
     * @param capacity The capacity of the special slot.
     */
    public SpecialSlot(int capacity) {
        super(capacity);

        base = false;
        standalone = true;

        operation = Operation.PREPARE;
    }

    /* */

    /**
     * Checks if the item associated with the slot is a base item.
     * @return true if the slot is a base slot, false otherwise.
     */
    public boolean isBase() {
        return base;
    }

    /**
     * Checks if the item associated with the slot is a standalone item.
     * @return true if the slot is a standalone slot, false otherwise.
     */
    public boolean isStandalone() {
        return standalone;
    }

    /**
     * Gets the operation to be performed on the items in the slot
     * @return The operation to be performed on the items in the slot
     */
    public Operation getOperation() {
        return operation;
    }

    /* */

    /**
     * Assigns the item associated to the slot along with other pertinent
     * information about the item. The item is assumed to be a standalone
     * non-base item that only has to be generally prepared.
     * @param item the item to assign to the slot.
     * @param price the unit price of the item in the slot.
     */
    @Override
    public void assignToItem(Item item, double price) {
        clearAssignment();
        super.assignToItem(item, price);
    }

    /**
     * Assigns the item associated to the slot along with other pertinent
     * information about the item.
     * @param item the item to assign to the slot.
     * @param price the unit price of the item in the slot.
     * @param base whether the slot is a base slot or not.
     * @param standalone whether the slot is a standalone slot or not.
     * @param operation the operation to be performed on the items in the slot
     */
    public void assignToItem(
        Item item,
        double price,
        boolean base,
        boolean standalone,
        Operation operation
    ) {
        assignToItem(item, price);

        this.base = base;
        this.standalone = standalone;
        this.operation = operation;
    }
    
    /**
     * Clears the slot of any assigned item and associated information and
     * resets it to the default values.
     */
    @Override
    public void clearAssignment() {
        super.assignToItem(null, 0.0);

        base = false;
        standalone = true;
        operation = Operation.PREPARE;
    }
}
