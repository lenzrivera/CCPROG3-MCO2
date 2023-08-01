package model.exceptions;

import model.DenominationMap;

/**
 * A type of exception which invovles returning the machine credit back
 * to the user. As an abstract class, it cannot be instantiated directly.
 */
public abstract class CreditException extends RuntimeException {
    /**
     * The returned credit.
     */
    private DenominationMap returnedCredit;
    
    /**
     * Constructs a new CreditException
     * @param returnedCredit the returned credit to the user
     */
    public CreditException(DenominationMap returnedCredit) {
        this.returnedCredit = returnedCredit;
    }

    /**
     * Gets the credit returned to the user
     * @return the credit returned to the user as a DenominationMap
     */
    public DenominationMap getReturnedCredit() {
        return returnedCredit;
    }
}
