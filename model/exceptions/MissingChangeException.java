package model.exceptions;

import model.DenominationMap;

/**
 * An exception thrown when there is the available denominations in the machine
 * cannot produce an exact change to complete a transaction.
 */
public class MissingChangeException extends CreditException {
    public MissingChangeException(DenominationMap returnedCredit) {
        super(returnedCredit);
    }
}