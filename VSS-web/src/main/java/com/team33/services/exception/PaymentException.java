package com.team33.services.exception;

/**
 * This exception is raised if the credit card cannot be validated
 *
 * @author Samual
 */
public class PaymentException extends Exception {

    /**
     * Constructs the exception that raises the message passed in
     *
     * @param msg
     */
    public PaymentException(String msg) {
        super(msg);
    }
}
