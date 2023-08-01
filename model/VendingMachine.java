package model;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.MissingChangeException;
import model.exceptions.InsufficientCreditException;

/**
 * This abstract class contains the functionality shared between regular
 * and special vending machines.
 *
 * @param <T> the type of slot used in the vending machine.
 */
public abstract class VendingMachine<T extends Slot> {
    /**
     * The minimum number of slots stored in a vending machine.
     */
    public static final int MIN_SLOT_COUNT = 8;

    /**
     * The name of the vending machine.
     */
    protected String name; 

    /**
     * The list of slots in the vending machine.
     */
    protected List<T> slots;

    /**
     * The number of slots in the vending machine.
     */
    protected int slotCount;

    /**
     * The capacity of each slot in the vending machine.
     */
    protected int slotCapacity;

    /**
     * The DenominationMap containing the inserted credit of the user
     */
    protected DenominationMap credit;

    /**
     * The DenominationMap containing the money stock of the machine
     */
    protected DenominationMap moneyStock;

    /**
     * The transaction summary of the vending machine since its last item 
     * (re)stocking.
     */
    protected Summary currentSummary;

    /**
     * Constructs a new VendingMachine instance with the specified name,
     * slot count, and slot capacity. Slot count falls back to a minimum
     * of MIN_SLOT_COUNT and slot capacity falls back to a minimum of 
     * Slot.MIN_MAX_CAPACITY.
     * @param name the name of the vending machine.
     * @param slotCount the total number of slots in the vending machine.
     * @param slotCapacity the maximum capacity of each slot.
     */
    public VendingMachine(String name, int slotCount, int slotCapacity) {
        this.name = name;

        slots = new ArrayList<>();
        this.slotCount = Math.max(MIN_SLOT_COUNT, slotCount);
        this.slotCapacity = Math.max(Slot.MIN_MAX_CAPACITY, slotCapacity);

        credit = new DenominationMap();
        moneyStock = new DenominationMap();

        currentSummary = new Summary(slots);
    }

    /* */

    /**
     * Returns the credit available for the machine to accept as payment to
     * dispense items.
     * @return the DenominationMap containing the denominations consisting the
     * available credit.
     */
    public DenominationMap getCredit() {
        return credit;
    }

    /**
     * Returns the name of the vending machine.
     * @return the name of the vending machine.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the money stock that the machine has to both provide chnage
     * and store income.
     * @return the DenominationMap containing the denominations consisting the
     * money stock.
     */
    public DenominationMap getMoneyStock() {
        return moneyStock;
    }

    /**
     * Returns the slot instance at the given slot number, if any.
     * @param slotNumber the 1-indexed number referring to a slot
     * @return the slot instance at the given slot number if it exists null. 
     * The type of the slot is the type associated with the vending machine.
     */
    public T getSlot(int slotNo) {
        return slots.get(slotNo - 1);
    }

    /**
     * Returns the array of slots which the vending machine has.
     * @return the array of slot instances of vending machine.
     */
    public List<T> getSlots() {
        return slots;
    }

    /**
     * Returns the number of slots that the vending machine has.
     * @return the number of slots that the vending machine has.
     */
    public int getSlotCount() {
        return slotCount;
    }

    /**
     * Returns the item capacity per slot in the vending machine.
     * @return the item capacity per slot in the vending machine.
     */
    public int getSlotCapacity() {
        return slotCapacity;
    }

    /**
     * Returns the transaction summary of the vending machine since its last
     * (re)stocking.
     * @return the Summary object representing the item transaction summary
     * since the last (re)stocking
     */
    public Summary getSummary() {
        return currentSummary;
    }

    /* */

    /**
     * Adds a item stored in a particular slot to the machine item selection.
     * @param slotNo the slot number (index + 1) of the slot containing the
     * item.
     */
    public abstract void addSelection(int slotNo);

    /**
     * Dispenses the selected items in a vending machine.
     * @return a DispenseResult object containing information about the 
     * dispensed item and transaction details.
     */
    public abstract DispenseResult dispenseSelection();

    /* */

    /**
     * Performs a transaction given the amount to pay, updating the machine's
     * credit and money stock maps, then returning the change from the payment.
     * @param paymentAmount The amount of money that should be paid
     * @return The DenominationMap representing the change to be returned.
     * @throws InsufficientCreditException if the user does not have sufficient
     * credit to pay.
     * @throws MissingChangeException if the vending machine cannot provide 
     * exact change.
     */
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
            throw new MissingChangeException(payment.getTotal());
        }

        // Transfer user payment from credit to moneyStock.

        for (Denomination denom : payment.getDenominations()) {
            credit.remove(denom, 1);
            moneyStock.add(denom, 1);
        }

        return change;
    }
}
