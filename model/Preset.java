package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Preset {
    private String name;
    private HashMap<String, Integer> items;
    private Operation operation;

    public Preset(String name, Operation operation) {
        this.name = name;
        items = new HashMap<>();
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public Set<Map.Entry<String, Integer>> getItems() {
        return items.entrySet();
    }
    
    public Operation getOperation() {
        return operation;
    }

    public void addItem(String itemName, int quantity) {
        items.put(itemName, quantity);
    }

    public void removeItem(String itemName) {
        items.remove(itemName);
    }
}
