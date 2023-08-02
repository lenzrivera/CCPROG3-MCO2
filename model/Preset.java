package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a combination of items which would make a whole
 * product that a special vending machine can dispense.
 */
public class Preset {
    /**
     * The name of the preset.
     */
    private String name;

    /**
     * The map containing the ingredients for the preset, i.e. the items and
     * the quantities of each item needed for the preset.
     */
    private Map<String, Integer> items;

    /**
     * The operation to apply to the whole preset result.
     */
    private Operation operation; 

    /**
     * The path to the image representing the preset.
     */
    private String imagePath;

    /**
     * Constructs a new Preset instance with the specified name, 
     * items with quantities, operation, and image path.
     *
     * @param name the name of the preset.
     * @param itemMap a map containing the items and their quantities for 
     * the preset.
     * @param operation the food preparation operation for the preset.
     * @param imagePath the path to the image of the preset.
     */
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

    /**
     * Gets the path to the image representing the preset.
     * @return the image path of the preset.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Gets the name of the preset.
     * @return the name of the preset.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the items and the quantities of each item needed for the preset.
     * @return the map of the items to their quantities.
     */
    public Map<String, Integer> getItems() {
        return items;
    }

    /**
     * Gets the operation to apply to the whole preset result.
     * @return the operation to apply to the whole preset result.
     */
    public Operation getOperation() {
        return operation;
    }
}
