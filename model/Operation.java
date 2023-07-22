package model;

// TODO: title case toString()

public enum Operation {
    BASE,
    COOKABLE,
    HEATABLE,
    TOPPABLE;

    public String getProcessMessage(String itemName) {
        switch (this) {
            case BASE:
                return "Preparing " + itemName + "...";

            case COOKABLE:
                return "Cooking " + itemName + "...";

            case HEATABLE:
                return "Heating " + itemName + "...";

            case TOPPABLE:
                return "Topping " + itemName + "...";

            default:
                return "Dispensing " + itemName + "...";
        }
    }
}
