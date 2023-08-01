package model;

/**
 * This class represents an item being sold in a vending machine. Each item
 * has a name and caloric content.  
 */
public class Item {
    /**
     * The name of the item.
     */
    private String name;

    /**
     * The caloric content of the item. 
     */
    private double calories;

    /**
     * The path of the image representing the item.
     */
    private String imagePath;
    
    /**
     * This constructor initializes an item from its name and caloric content.
     * @param name the name of the item
     * @param calories the caloric content of the item
     * @param imagePath the path of the image representing the item
     */
    public Item(String name, double calories, String imagePath) {
        this.name = name;
        this.calories = calories;
        this.imagePath = imagePath;
    }

    /**
     * Constructs an item from an existing item, copying it.
     * @param toCopy the Item object to copy
     */
    public Item(Item toCopy) {
        name = toCopy.name;
        calories = toCopy.calories;
        imagePath = toCopy.imagePath;
    }
    
    /**
     * Returns the path of the image representing the item. 
     * @return the path of the image representing the item.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns the name of the item.
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the caloric content of the item.
     * @return the caloric content of the item.
     */
    public double getCalories() {
        return calories;
    }
}
