package model;

public class SpecialSlot extends Slot {
    private Operation itemOperation;

    private boolean standalone;

    public SpecialSlot(int capacity) {
        super(capacity);
    }

    public Operation getItemOperation() {
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
        sampleItem = new Item(name, calories, imagePath);
        this.unitPrice = price;
        this.standalone = true;
        this.itemOperation = null;
    }

    public void assignToItem(
        String name,
        double price,
        double calories,
        String imagePath,
        boolean standalone,
        Operation operation
    ) {
        assignToItem(name, price, calories, imagePath);

        this.standalone = standalone;
        this.itemOperation = operation;
    }
}
