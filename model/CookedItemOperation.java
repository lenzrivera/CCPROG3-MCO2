package model;

public class CookedItemOperation implements ItemOperation {
    public CookedItemOperation() {
    }
    
    @Override
    public String getProcessMessage(String itemName) {
        return "Cooking" + " " + itemName + "...";
    }
}
