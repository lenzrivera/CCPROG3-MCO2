package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum represents a valid denomination that can be fed into the 
 * vending machine. Moreover, it also provides utility methods to enumerate
 * and check denominations and/or prices.
 */
public enum Denomination {
    /**
     * 25 cents
     */
    C25(0.25),

    /**
     * 1 Peso
     */
    P1(1.0),

    /**
     * 5 Pesos
     */
    P5(5.0),

    /**
     * 10 Pesos
     */
    P10(10.0),

    /**
     * 20 Pesos
     */
    P20(20.0),

    /**
     * 50 Pesos
     */
    P50(50.0),

    /**
     * 100 Pesos
     */
    P100(100.0),

    /**
     * 200 Pesos
     */
    P200(200.0),

    /**
     * 500 Pesos
     */
    P500(500.0),

    /**
     * 1000 Pesos
     */
    P1000(1000.0);

    /**
     * The value of the denomination.
     */
    private double value;

    /**
     * Associates a denomination value with an enum entry.
     * @param value The value of the denomination.
     */
    private Denomination(double value) {
        this.value = value;
    }

    /**
     * Get the value of the denomination.
     * @return the value of the denomination.
     */
    public double getValue() {
        return value;
    }

    /**
     * A static method to get a list of all double values of the valid 
     * denominations.
     * @return a list containing the double values of all denominations.
     */
    public static List<Double> getDoubleValues() {
        List<Double> valueList = new ArrayList<>();

        for (Denomination denom : values()) {
            valueList.add(denom.getValue());
        }

        return valueList;
    }

    /**
     * A static method to check if a given price is a sum of valid denominations.
     * @param price the price to check for validity.
     * @return true if the price is valid, otherwise false.
     */
    public static boolean isValidPrice(double price) {
        // Essentially any whole number price is allowed; the only constraints
        // would be with the decimal part, which should only be multiples of
        // 0.25.
        double frac = price - (int) price;

        // Account for potential rounding errors.
        return frac % 0.25 < 0.00001;
    }

    /**
     * A static method to convert a double value to the corresponding 
     * Denomination enum constant.
     * @param amount the double value to convert to a Denomination enum constant.
     * @return the corresponding `Denomination` enum constant if valid, 
     * otherwise null.
     */
    public static Denomination toEnum(double amount) {
        for (Denomination d : values()) {
            if (d.getValue() == amount) {
                return d;
            }
        }

        return null;
    }
}
