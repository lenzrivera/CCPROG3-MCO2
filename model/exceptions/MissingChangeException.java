package model.exceptions;

import model.DenominationMap;

/**
 * An exception thrown when there is the available denominations in the machine
 * cannot produce an exact change to complete a transaction.
 */
public class MissingChangeException extends CreditException {
    /**
     * Constructs a new MissingChangeException instance.
     * @param returnedCredit the credit to return to the user
     */
    public MissingChangeException(DenominationMap returnedCredit) {
        super(returnedCredit);
    }

    @Override
    public String getMessage() {
        return "The machine cannot provide exact change.";
    }
}