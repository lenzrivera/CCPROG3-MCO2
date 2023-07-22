package model;

public class Item {
    private String name;
    private double calories;

    public Item(String name, double calories) {
        this.name = name;
        this.calories = calories;
    }

    public Item(Item toCopy) {
        name = toCopy.name;
        calories = toCopy.calories;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }
}
