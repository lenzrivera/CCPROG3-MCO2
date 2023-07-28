package model;

import java.util.List;

public class DispenseResult {
    private String name;

    private List<Item> items;
    private List<String> processMessages;

    private DenominationMap change;
    private double totalCalories;
    private double totalPayment;

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
        this.change = change;
        this.totalCalories = totalCalories;
        this.totalPayment = totalPayment;
    }

    /* */

    public DenominationMap getChange() {
        return change;
    }


    public List<Item> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public List<String> getProcessMessages() {
        return processMessages;
    }

    public double getTotalCalories() {
        return totalCalories;
    }
    
    public double getTotalPayment() {
        return totalPayment;
    }
}
