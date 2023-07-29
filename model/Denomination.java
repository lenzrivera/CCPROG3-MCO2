package model;

import java.util.ArrayList;
import java.util.List;

public enum Denomination {
    C25(0.25),
    P1(1.0),
    P5(5.0),
    P10(10.0),
    P20(20.0),
    P50(50.0),
    P100(100.0),
    P200(200.0),
    P500(500.0),
    P1000(1000.0);

    private double value;

    private Denomination(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static List<Double> getDoubleValues() {
        List<Double> valueList = new ArrayList<>();

        for (Denomination denom : values()) {
            valueList.add(denom.getValue());
        }

        return valueList;
    }

    public static boolean isValidPrice(double price) {
        // Essentially any whole number price is allowed; the only constraints
        // would be with the decimal part which should only be multiples of
        // 0.25.
        double frac = price - (int) price;

        // Account for potential rounding errors.
        return frac % 0.25 < 0.00001;
    }

    public static Denomination toEnum(double amount) {
        for (Denomination d : values()) {
            if (d.getValue() == amount){
                return d;
            }
        }

        return null;
    }
}
