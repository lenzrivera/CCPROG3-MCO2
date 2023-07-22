package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Preset {
    private String name;

    private HashMap<String, Integer> items;

    public Preset(String name) {
        this.name = name;
        items = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Set<Map.Entry<String, Integer>> getItems() {
        return items.entrySet();
    }
    
    public void addItem(String itemName, int quantity) {
        items.put(itemName, quantity);
    }

    public void removeItem(String itemName) {
        items.remove(itemName);
    }
}
