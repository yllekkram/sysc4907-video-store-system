package com.team33.services.exception;

/**
 * This exception occurs when a selected payment type cannot be charged due to
 * lack of funds
 *
 * @author Samual
 */
public class InsufficientFundsException extends Exception {

    /**
     * Constructs the exception that raises the message passed in
     *
     * @param msg
     */
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
