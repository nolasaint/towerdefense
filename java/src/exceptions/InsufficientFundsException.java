package net.nolasaint.towerdefense.exceptions;

/**
 * This class defines an exception which will be thrown in the event of some
 * shortage of money or other funds.
 *
 * @author Evan Bailey
 * @version 1.0
 */
public class InsufficientFundsException extends Exception {

    /**
     * Constructs a new InsufficientFundsException with the specified error
     * message.
     *
     * @param   msg - the custom error message
     */
    public InsufficientFundsException(String msg) {
        super(msg);

    }

}
