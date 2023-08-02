package model.exceptions;

import model.DenominationMap;

/**
 * An exception thrown when there is insufficient credit available to complete 
 * a transaction.
 */
public class InsufficientCreditException extends CreditException {
    /**
     * Constructs a new InsufficientCreditException instance.
     * @param returnedCredit the credit to return to the user
     */
    public InsufficientCreditException(DenominationMap returnedCredit) {
        super(returnedCredit);
    }

    @Override
    public String getMessage() {
        return "There is insufficient credit inserted.";
    }
}
