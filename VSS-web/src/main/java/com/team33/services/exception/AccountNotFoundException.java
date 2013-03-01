package com.team33.services.exception;

/**
 * This exception is raised when an account cannot be found when a user attempt
 * retrieval
 *
 * @author Samual
 */
public class AccountNotFoundException extends Exception {

    /**
     * Constructs the exception that raises the message passed in
     *
     * @param message
     */
    public AccountNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs the exception that raises the message passed in and throws the
     * exception as the throw-able type passed in
     *
     * @param message
     * @param throwable
     */
    public AccountNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
