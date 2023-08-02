package model;

/**
 * The Operation enum represents different food preparation operations 
 * in the context of a vending machine.
 */
public enum Operation {
    /**
     * The item should be cooked.
     */
    COOK(3),

    /**
     * The item should be heated.
     */
    HEAT(4),

    /**
     * The item should be "prepared" in a general sense.
     */
    PREPARE(0),
    
    /**
     * The item should be spread.
     */
    SPREAD(1),

    /**
     * The item should be topped.
     */
    TOP(2);

    /**
     * The precedence of the operation
     */
    private int precedence;

    /**
     * Assigns the values associated with an Operation.
     * @param precedence the precedence of an operation
     */
    private Operation(int precedence) {
        this.precedence = precedence;
    }

    /**
     * Returns the precedence of an operation, smaller values indicating
     * that they should be performed earlier.
     * @return the operation precedence
     */
    public int getPrecedence() {
        return precedence;
    }

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
