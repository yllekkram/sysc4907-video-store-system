package com.team33.services.exception;

/**
 * This running time exception would occur with invalid data access parameters
 *
 * @author Samual
 */
public class DataAccessException extends RuntimeException {

    /**
     * Constructs the exception that raises the message passed in
     *
     * @param msg
     */
    public DataAccessException(String msg) {
        super(msg);
    }
}
