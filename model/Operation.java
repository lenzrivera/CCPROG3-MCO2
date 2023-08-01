package model;

/**
 * The Operation enum represents different food preparation operations 
 * in the context of a vending machine.
 */
public enum Operation {
    /**
     * The item should be cooked.
     */
    COOK,

    /**
     * The item should be heated.
     */
    HEAT,

    /**
     * The item should be "prepared" in a general sense.
     */
    PREPARE,
    
    /**
     * The item should be spread.
     */
    SPREAD,

    /**
     * The item should be topped.
     */
    TOP;

    /**
     * Get the process message for the operation and the given item name.
     * @param itemName The name of the item being prepared.
     * @return the process message corresponding to the operation and the item
     * name.
     */
    public String getProcessMessage(String itemName) {
        switch (this) {
            case COOK:
                return "Cooking " + itemName + "...";

            case HEAT:
                return "Heating " + itemName + "...";

            case SPREAD:
                return "Spreading " + itemName + "...";

            case PREPARE:
                return "Preparing " + itemName + "...";

            case TOP:
                return "Topping " + itemName + "...";

            default:
                return "Dispensing " + itemName + "...";
        }
    }
}
