package model;

import java.util.HashMap;
import java.util.Map;

public class Preset {
    private String name;
    private Map<String, Integer> items;
    private Operation operation;
    private String imagePath;

    public Preset(
        String name, 
        Map<String, Integer> itemMap, 
        Operation operation, 
        String imagePath
    ) {
        this.name = name;
        items = new HashMap<>(itemMap);
        this.operation = operation;
        this.imagePath = imagePath;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public Operation getOperation() {
        return operation;
    }
}
