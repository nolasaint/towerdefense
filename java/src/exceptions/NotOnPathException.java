package net.nolasaint.towerdefense.exceptions;

import net.nolasaint.towerdefense.Path;

/**
 * This class defines an exception which is thrown when an attempt to find the
 * next Point on a Path is made using a Point not along said Path.
 *
 * @author Evan Bailey
 * @version 1.0
 * @see Path
 */
public class NotOnPathException extends Exception {

    /**
     * Constructs a new NotOnPathException with the specified error message.
     *
     * @param   msg - the custom error message
     */
    public NotOnPathException(String msg) {
        super(msg);

    }

}
