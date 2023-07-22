package model;

public class BaseItemOperation implements ItemOperation {
    public BaseItemOperation() {
    }
    
    @Override
    public String getProcessMessage(String itemName) {
        return "Preparing" + " " + itemName + "...";
    }
}
