package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The DenominationMap class represents a collection of denominations. Each
 * denomination in the class corresponds to a list of one denomination value
 * to simulate an actual money holder.
 */
public class DenominationMap {
    /**
     * The map of denominations to their list storing the each of such
     * denomination. The denominations are stored in descending order to
     * facilitate computations.
     */
    private Map<Denomination, List<Denomination>> denominations;

    /**
     * Constructs an empty DenominationMap.
     */
    public DenominationMap() {
        denominations = new TreeMap<>(Collections.reverseOrder());
    }

    /**
     * Constructs a new DenominationMap by copying an existing one.
     * @param denomMap the DenominationMap to copy.
     */
    public DenominationMap(DenominationMap denomMap) {
        this.denominations = new TreeMap<>(denomMap.denominations);
    }

    /**
     * Gets a list of all individual denominations in the DenominationMap in
     * descending order.
     * @return a list containing all the denominations in the DenominationMap.
     */
    public List<Denomination> getDenominations() {
        return denominations.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * Returns a map of each stored denomination to the number of such 
     * denomination stored in the DenominationMap.
     * @return a map containing each stored denomination mapped to its quantity
     * stored in the DenominationMap.
     */
    public Map<Denomination, Integer> getQuantityMap() {
        Map<Denomination, Integer> qtyMap = new LinkedHashMap<>();

        for (var entry : denominations.entrySet()) {
            qtyMap.put(entry.getKey(), entry.getValue().size());
        }

        return qtyMap;
    }

    /**
     * Calculates the total value of all denominations in the DenominationMap.
     * @return the total value of all denominations.
     */
    public double getTotal() {
        double total = 0.0;

        for (var denom : denominations.entrySet()) {
            total += denom.getKey().getValue() * denom.getValue().size();
        }

        return total;
    }

    /**
     * Adds a certain quantity of a denomination to the DenominationMap.
     * @param denom the denomination to add.
     * @param quantity the quantity of the denomination to add.
     * @throws IllegalArgumentException if the quantity is less than zero.
     */
    public void add(Denomination denom, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        if (denominations.get(denom) == null) {
            denominations.put(denom, new ArrayList<>());
        }

        for (int i = 0; i < quantity; i++) {
            denominations.get(denom).add(denom);
        }
    }

    /**
     * Collects all the denominations in the DenominationMap, emptying it
     * and returing a new one containing the collected denominations.
     * @return a new DenominationMap containing the collected denominations.
     */
    public DenominationMap collect() {
        DenominationMap toCollect = new DenominationMap(this);
        denominations.clear();

        return toCollect;
    }

    /**
     * Removes a certain quantity of a denomination from the DenominationMap.
     * @param denom the denomination to remove.
     * @param quantity the quantity of the denomination to remove.
     * @return true if the denomination and quantity were successfully removed, 
     * otherwise false.
     */
    public boolean remove(Denomination denom, int quantity) {
        if (denominations.get(denom) == null || denominations.get(denom).size() < quantity) {
            return false;
        }

        List<Denomination> stock = denominations.get(denom);

        for (int i = 0; i < quantity; i++) {
            stock.remove(0);
        }

        if (stock.size() == 0) {
            denominations.remove(denom);
        }

        return true;
    }
}
