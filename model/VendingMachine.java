package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class VendingMachine {
    public static final int MIN_SLOT_COUNT = 8;

    private static double matchTransfer(
        double amount, 
        DenominationMap src, 
        DenominationMap dst, 
        DenominationMap basis
    ) {
        double matchAmount = 0.0;

        for (
            Map.Entry<Double, Integer> entry : 
                basis.getDenominations().entrySet()
        ) {
            double denomination = entry.getKey();

            src.remove(denomination, 1);
            matchAmount += denomination;
            dst.add(denomination, 1);

            if (matchAmount >= amount) {
                return matchAmount;
            }
        }

        return -1;
    }

    protected String name;

    protected ArrayList<Slot> slots;

    protected DenominationMap changeToGive;

    protected DenominationMap credit;

    protected DenominationMap changeStock;

    protected DenominationMap payments;

    protected Summary currentSummary;

    public VendingMachine(String name) {
        this.name = name;

        slots = new ArrayList<>();

        changeToGive = new DenominationMap();
        credit = new DenominationMap();

        changeStock = new DenominationMap();
        payments = new DenominationMap();

        currentSummary = new Summary(slots);
    }

    // Accessors //

    public DenominationMap getChangeStock() {
        return changeStock;
    }
    
    public Slot getSlot(int slotNo) {
        return slots.get(slotNo - 1);
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public int getSlotCount() {
        return slots.size();
    }

    public int getSlotCapacity() {
        return slots.get(0).getCapacity();
    }

    public Summary getSummary() {
        return currentSummary;
    }

    // Vending Methods //

    protected boolean computeChangeFor(double amount) {
        // Generate a temporary empty denomination map containing all the
        // valid denominations to facilitate the computation for change.
        //
        // This also results in the temporary maps below to have sorted
        // keys in descending order, important in the change computation 
        // process.
        LinkedHashMap<Double,Integer> denominations = new LinkedHashMap<>();
        for (double denom : DenominationMap.VALID_DENOMINATIONS) {
            denominations.put(denom,0);
        }

        DenominationMap tempCredit = new DenominationMap(denominations);
        DenominationMap tempChangeStock = new DenominationMap(denominations);
        DenominationMap tempPayment = new DenominationMap(denominations);
        DenominationMap tempChangeToGive = new DenominationMap(denominations);

        // Copy over the existing denominations from the credit map, marking
        // denominations not in the map as well.
        for (
            Map.Entry<Double, Integer> denom : 
                credit.getDenominations().entrySet()
        ) {
            double denomination = denom.getKey();
            int quantity = denom.getValue();
            tempCredit.add(denomination, quantity);
        }

        // Copy over the existing denominations from the stocked change map, 
        // marking denominations not in the map as well.
        for (
            Map.Entry<Double, Integer> denom : changeStock.getDenominations().entrySet()
        ) {
            double denomination = denom.getKey();
            int quantity = denom.getValue();
            tempChangeStock.add(denomination, quantity);
        }

        // Match the item price based on the available denominations in the
        // machine's credit map. Ready those denominations to act as the
        // payment to the item as well.
        double paymentMatchAmount = 
            matchTransfer(amount, tempCredit, tempPayment, credit);

        // Don't proceed if there is not enough credit in the first place.
        if (paymentMatchAmount == -1) {
            return false;
        }

        // Check if there is change and if the change can be provided.
        double change = paymentMatchAmount - amount;
        double changeMatchAmount =
            matchTransfer(change, tempChangeStock, tempChangeToGive, changeStock);

        // Unlike matching the payment, providing a change greater than needed
        // is considered to be a case of insufficient denominations in the change
        // stock.
        if (changeMatchAmount == -1 || changeMatchAmount > change) {
            return false;
        }

        // Given that the denominations in the credit map that will represent
        // the payment are now known, transfer them from the credit map to the
        // payments map.
        for (
            Map.Entry<Double, Integer> denom : 
                tempPayment.getDenominations().entrySet()
        ) {
            double denomination = denom.getKey();
            int value = denom.getValue();

            credit.remove(denomination, value);
            payments.add(denomination, value);
        }

        // Likewise, now that the denominations representing the change (if any)
        // are known, reflect them in the corresponding maps.
        for (
            Map.Entry<Double, Integer> denom : 
                tempChangeToGive.getDenominations().entrySet()
        ) {
            double denomination = denom.getKey();
            int value = denom.getValue();

            changeStock.remove(denomination, value);

            // Don't store empty denominations back to the actual denomination 
            // map.
            if (value != 0) {
                changeToGive.add(denomination,value);
            }
        }

        return true;
    }

    public abstract DispenseResult dispenseSelected();

    public void insertCredit(double denom, int quantity) {
        credit.add(denom, quantity);
    }

    public abstract void resetSelection();

    public DenominationMap returnCredit() {
        return credit.collect();
    }

    public abstract void selectItem(int slotNo);

    // Maintenance Methods //

    public boolean addItem(
        int slotNo, 
        String name, 
        double price, 
        double calories
    ) {
        Slot slot = slots.get(slotNo - 1);

        if (slot.getSampleItem() != null) {
            return false;
        }

        slot.assignToItem(name, price, calories);
        return true;
    }

    public boolean removeItem(int slotNo) {
        Slot slot = slots.get(slotNo - 1);

        if (slot.getSampleItem() == null) {
            return false;
        }

        slot.clearAssignment();
        return true;
    }

    public void stockChange(double denom, int quantity) {
        changeStock.add(denom, quantity);
    }

    public void stockItem(int slotNo, int quantity) {
        Slot slot = slots.get(slotNo - 1);
        slot.stockItem(quantity);
    }
}
