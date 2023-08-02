package model;

import java.util.List;

/**
 * Represents the result of a vending machine transaction. It contains 
 * information about the name of the dispensed product, the dispensed items,
 * the change given, among others.
 */
public class DispenseResult {
    /**
     * The name of the dispened product.
     */
    private String name;

    /**
     * The list of items dispensed during the transaction.
     */
    private List<Item> items;

    /**
     * List of messages describing the preparation of the product before
     * being dispensed.
     */
    private List<String> processMessages;

    /**
     * A DenominationMap representing the change returned to the user.
     */
    private DenominationMap change;

    /**
     * The total calories of all the dispensed items.
     */
    private double totalCalories;

    /**
     * The total payment received for the transaction.
     */
    private double totalPayment;

    /**
     * Constructs a new DispenseResult object.
     * @param name the name of the product to dispense.
     * @param items the list of items dispensed during the transaction.
     * @param processMessages the list of messages describing the preparation
     * process.
     * @param change a DenominationMap representing the change returned to 
     * the user.
     * @param totalCalories the total calories of all the dispensed items.
     * @param totalPayment the total payment received for the transaction.
     */
    public DispenseResult(
        String name,
        List<Item> items,
        List<String> processMessages,
        DenominationMap change,
        double totalCalories,
        double totalPayment
    ) {
        this.name = name;
        this.items = items;
        this.processMessages = processMessages;
        this.change = change;
        this.totalCalories = totalCalories;
        this.totalPayment = totalPayment;
    }

    /* */

    /**
     * Gets the change returned to the user in the form of a DenominationMap.
     * @return The DenominationMap representing the change.
     */
    public DenominationMap getChange() {
        return change;
    }

    /**
     * Gets the list of items dispensed during the transaction.
     * @return The list of items dispensed.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Gets the name of the dispensed product.
     * @return the name of the dispensed product
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of messages describing the preparation of the product 
     * before being dispensed.
     * @return the list of process messages.
     */
    public List<String> getProcessMessages() {
        return processMessages;
    }

    /**
     * Gets the total calories of all the dispensed items.
     * @return The total calories of the dispensed items.
     */
    public double getTotalCalories() {
        return totalCalories;
    }

    /**
     * Gets the total payment received for the transaction.
     * @return The total payment received for the transaction.
     */
    public double getTotalPayment() {
        return totalPayment;
    }
}
