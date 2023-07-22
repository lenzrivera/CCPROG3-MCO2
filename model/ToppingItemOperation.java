package model;

public class ToppingItemOperation implements ItemOperation {
    public ToppingItemOperation() {
    }
    
    @Override
    public String getProcessMessage(String itemName) {
        return "Topping" + " " + itemName + "...";
    }
}
