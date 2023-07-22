package model;

public class Item {
    private String name;
    private double calories;
    private String imagePath;

    public Item(String name, double calories, String imagePath) {
        this.name = name;
        this.calories = calories;
        this.imagePath = imagePath;
    }

    public Item(Item toCopy) {
        name = toCopy.name;
        calories = toCopy.calories;
        imagePath = toCopy.imagePath;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }
}
