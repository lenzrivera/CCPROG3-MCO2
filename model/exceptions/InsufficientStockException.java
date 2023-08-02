package model.exceptions;

import model.DenominationMap;

/**
 * An exception thrown when there is insufficient stock available to complete 
 * a transaction.
 */
public class InsufficientStockException extends CreditException {
    /**
     * Constructs a new InsufficientStockException instance.
     * @param returnedCredit the credit to return to the user
     */
    public InsufficientStockException(DenominationMap returnedCredit) {
        super(returnedCredit);
    }
    
    @Override
    public String getMessage() {
        return "There is insufficient item stock.";
    }
}
