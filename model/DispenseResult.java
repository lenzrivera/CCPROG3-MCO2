package model;

import java.util.ArrayList;
import java.util.List;

public class DispenseResult {
    private String name;

    private ArrayList<Item> items;

    private ArrayList<String> processMessages;

    private DenominationMap change;

    private double totalPayment;

    public DispenseResult() {
        this.name = "";

        items = new ArrayList<>();
        processMessages = new ArrayList<>();

        change = new DenominationMap();
        totalPayment = 0;
    }

    // Accessors //

    public DenominationMap getChange() {
        return change;
    }

    public void setChange(DenominationMap v) {
        change = v;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void setName(String v) {
        name = v;
    }

    public List<String> getProcessMessages() {
        return processMessages;
    }
    
    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double v) {
        totalPayment = v;
    }


    // Main Methods //

    public void addItem(Item item) {
        items.add(item);
    }

    public void addProcessMessage(String message) {
        processMessages.add(message);
    }
}
