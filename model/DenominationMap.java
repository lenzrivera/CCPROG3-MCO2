package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DenominationMap {
    private Map<Denomination, List<Denomination>> denominations;

    public DenominationMap() {
        denominations = new TreeMap<>(Collections.reverseOrder());
    }

    public DenominationMap(DenominationMap denomMap) {
        this.denominations = new TreeMap<>(denomMap.denominations);
    }

    /* */

    public List<Denomination> getDenominations() {
        return denominations.values().stream()
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
    }

    public Map<Denomination, Integer> getQuantityMap() {
        Map<Denomination, Integer> qtyMap = new LinkedHashMap<>();

        for (var entry : denominations.entrySet()) {
            qtyMap.put(entry.getKey(), entry.getValue().size());
        }

        return qtyMap;
    }

    public double getTotal() {
         double total = 0.0;

        for (var denom : denominations.entrySet()) {
            total += denom.getKey().getValue() * denom.getValue().size();
        }

        return total;       
    }

    /* */

    public void add(Denomination denom, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException();
        }

        if (denominations.get(denom) == null) {
            denominations.put(denom, new ArrayList<>());
        }

        for (int i = 0; i < quantity; i++) {
            denominations.get(denom).add(denom);
        }
    }

    public DenominationMap collect() {
        DenominationMap toCollect = new DenominationMap(this);
        denominations.clear();

        return toCollect;
    }

    public boolean remove(Denomination denom, int quantity) {
        if (
            denominations.get(denom) == null ||    
            denominations.get(denom).size() < quantity 
        ) {
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
