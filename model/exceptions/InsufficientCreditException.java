package model.exceptions;

import model.DenominationMap;

/**
 * An exception thrown when there is insufficient credit available to complete 
 * a transaction.
 */
public class InsufficientCreditException extends CreditException {
    public InsufficientCreditException(DenominationMap returnedCredit) {
        super(returnedCredit);
    }
}
