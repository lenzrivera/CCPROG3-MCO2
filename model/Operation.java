package model;

public enum Operation {
    COOK,
    HEAT,
    PREPARE,
    SPREAD,
    TOP;

    public String getProcessMessage(String itemName) {
        switch (this) {
            case COOK:
                return "Cooking " + itemName + "...";

            case HEAT:
                return "Heating " + itemName + "...";

            case SPREAD:
                return "Spreading " + itemName + "...";

            case PREPARE:
                return "Preparing " + itemName + "...";

            case TOP:
                return "Topping " + itemName + "...";

            default:
                return "Dispensing " + itemName + "...";
        }
    }
}