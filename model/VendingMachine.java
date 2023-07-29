package model;

import java.util.List;

import model.exceptions.InsufficientChangeException;
import model.exceptions.InsufficientCreditException;

public abstract class VendingMachine<T extends Slot> {
    public static final int MIN_SLOT_COUNT = 8;

    protected String name;

    protected List<T> slots;
    protected int slotCount;
    protected int slotCapacity;

    protected DenominationMap credit;
    protected DenominationMap moneyStock;

    protected Summary currentSummary;

    public VendingMachine(String name, int slotCount, int slotCapacity) {
        this.name = name;
        this.slotCount = Math.max(MIN_SLOT_COUNT, slotCount);
        this.slotCapacity = Math.max(Slot.MIN_MAX_CAPACITY, slotCapacity);
    }

    /* */

    public DenominationMap getCredit() {
        return credit;
    }

    public DenominationMap getMoneyStock() {
        return moneyStock;
    }

    public T getSlot(int slotNo) {
        return slots.get(slotNo - 1);
    }

    public List<T> getSlots() {
        return slots;
    }

    public int getSlotCount() {
        return slotCount;
    }

    public int getSlotCapacity() {
        return slotCapacity;
    }

    public Summary getSummary() {
        return currentSummary;
    }

    /* */

    public abstract void addSelection(int slotNo);

    public abstract DispenseResult dispenseSelection();

    /* */

    protected DenominationMap transact(double paymentAmount) {
        DenominationMap tempCredit = new DenominationMap(credit);
        DenominationMap payment = new DenominationMap();

        // Match paymentAmount at least from denominations in credit.

        for (Denomination denom : tempCredit.getDenominations()) {
            tempCredit.remove(denom, 1);
            payment.add(denom, 1);

            if (payment.getTotal() >= paymentAmount) {
                break;
            }
        }

        if (payment.getTotal() < paymentAmount) {
            throw new InsufficientCreditException(paymentAmount, credit.getTotal());
        }

        double changeAmount = payment.getTotal() - paymentAmount;

        DenominationMap tempMoneyStock = new DenominationMap(moneyStock);
        DenominationMap change = new DenominationMap();

        // Match changeAmount exactly from denominations in moneyStock.

        for (Denomination denom : tempMoneyStock.getDenominations()) {
            if (change.getTotal() + denom.getValue() > changeAmount) {
                continue;
            }

            tempMoneyStock.remove(denom, 1);
            change.add(denom, 1);
        }

        if (change.getTotal() != changeAmount) {
            throw new InsufficientChangeException(payment.getTotal());
        }

        // Transfer user payment from credit to moneyStock.

        for (Denomination denom : payment.getDenominations()) {
            credit.remove(denom, 1);
            moneyStock.add(denom, 1);
        }

        return change;
    }
}
