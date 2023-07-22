package model;

import java.util.Map;
import java.util.LinkedHashMap;

public class DenominationMap {
    /**
     * The array of valid denominations for the vending machine.
     */
    public static final double[] VALID_DENOMINATIONS = {
        1000.0,
        500.0,
        200.0,
        100.0,
        50.0,
        20.0,
        10.0,
        5.0,
        1.0,
        0.25
    };

    /**
     * Determines whether a given denomination is valid in the context of the
     * vending machine. Valid denominations are all denominations in the present
     * series of Philippine currency except for 5 and 1 centavos.
     * @param denom the denomination whose validity will be checked
     * @return <code>true</code> if the denomination is valid, <code>false
     * </code> otherwise.
     */
    public static boolean isValidDenomination(double denom) {
        for (double d : VALID_DENOMINATIONS) {
            if (d == denom){
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether a given price is valid, i.e. can be obtained as a sum
     * of the valid denominations.
     * @param price the price whose validity will be checked
     * @return <code>true</code> if the price is valid, <code>false</code>
     * otherwise.
     */
    public static boolean isValidPrice(double price) {
        // Essentially any whole number price is allowed; the only constraints
        // would be with the decimal part which should only be multiples of
        // 0.25.
        double frac = price - (int) price;

        // Account for potential rounding errors.
        return frac % 0.25 < 0.00001;
    }

    private LinkedHashMap<Double, Integer> denominations;

    public DenominationMap() {
        denominations = new LinkedHashMap<>();
    }

    public DenominationMap(LinkedHashMap<Double, Integer> denominations) {
        this.denominations = new LinkedHashMap<>(denominations);
    }

    public Map<Double, Integer> getDenominations() {
        // TODO: replace with less hacky code if possible
        
        // Not making a copy of the hashmap can cause 
        // ConcurrentModificationException in some use cases.
        return new LinkedHashMap<>(denominations);
    }

    public double getTotal() {
         double total = 0.0;

        for (Map.Entry<Double, Integer> denom : denominations.entrySet()) {
            total += denom.getKey() * denom.getValue();
        }

        return total;       
    }

    /**
     * Determines whether the DenominationMap has no contents.
     * @return <code>true</code> is the DenominationMap is empty, <code>false
     * </code> otherwise.
     */
    public boolean isEmpty() {
        return denominations.size() == 0;
    }

    public void add(double denom, int quantity) {
        if (quantity < 0 || !isValidDenomination(denom)) {
            throw new IllegalArgumentException();
        }

        if (denominations.get(denom) == null) {
            denominations.put(denom, quantity);
        } else {
            denominations.put(denom, denominations.get(denom) + quantity);
        }       
    }

    public DenominationMap collect() {
        DenominationMap toCollect = new DenominationMap(denominations);
        denominations.clear();

        return toCollect;
    }

    public boolean remove(double denom, int quantity) {
        if (!isValidDenomination(denom)) {
            throw new IllegalArgumentException();
        }

        if (denominations.get(denom) < quantity || 
            denominations.get(denom) == null
        ) {
            return false;
        }

        int newStock = denominations.get(denom) - quantity;

        if (newStock == 0) {
            denominations.remove(denom);
        } else {
            denominations.put(denom, newStock);
        }

        return true;
    }
}
