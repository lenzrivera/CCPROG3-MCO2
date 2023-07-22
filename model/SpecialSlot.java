package model;

public class SpecialSlot extends Slot {
    private ItemOperation itemOperation;

    private boolean standalone;

    public SpecialSlot(int capacity) {
        super(capacity);
    }

    public ItemOperation getItemOperation() {
        return itemOperation;
    }

    public boolean isStandalone() {
        return standalone;
    }

    @Override
    public void assignToItem(
        String name, 
        double price, 
        double calories,
        String imagePath
    ) {
        assignToItem(name, price, calories, imagePath, true, null);
    }

    public void assignToItem(
        String name,
        double price,
        double calories,
        String imagePath,
        boolean standalone,
        ItemOperation operation
    ) {
        assignToItem(name, price, calories, imagePath);

        this.standalone = standalone;
        this.itemOperation = operation;
    }
}
