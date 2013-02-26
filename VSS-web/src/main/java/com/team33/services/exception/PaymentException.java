/*
 * Raised if credit card cannot be validated
 */
package com.team33.services.exception;

/**
 *
 * @author Samual
 */
public class PaymentException extends Exception {

    /**
     *
     * @param msg
     */
    public PaymentException(String msg) {
        super(msg);
    }
}
