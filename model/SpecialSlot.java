package model;

public class SpecialSlot extends Slot {
    private boolean base;
    private boolean standalone;

    private Operation operation;

    public SpecialSlot(int capacity) {
        super(capacity);

        base = false;
        standalone = true;

        operation = Operation.PREPARE;
    }

    /* */

    public boolean isBase() {
        return base;
    }

    public boolean isStandalone() {
        return standalone;
    }

    public Operation getOperation() {
        return operation;
    }

    /* */

    @Override
    public void assignToItem(Item item, double price) {
        clearAssignment();
        super.assignToItem(item, price);     
    }

    public void assignToItem(
        Item item,
        double price,
        boolean base,
        boolean standalone,
        Operation operation
    ) {
        assignToItem(item, price);

        this.base = base;
        this.standalone = standalone;
        this.operation = operation;
    }

    @Override
    public void clearAssignment() {
        assignToItem(null, 0.0, false, true, Operation.PREPARE);
    }
}
