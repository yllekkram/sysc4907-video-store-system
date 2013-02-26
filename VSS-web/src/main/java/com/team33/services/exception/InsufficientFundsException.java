/*
 * When a selected payment type cannot be charged due to lack of funds
 */
package com.team33.services.exception;

/**
 *
 * @author Samual
 */
public class InsufficientFundsException extends Exception {

    /**
     *
     * @param msg
     */
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
