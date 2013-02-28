
package com.team33.services.exception;

/**
 * This exception is triggered when a user attempts an action
 * only applicable to active users
 * @author Samual
 */
public class AccountNotActivatedException extends Exception{
    /**
     * Constructs the exception that raises the message passed in
     * @param message
     */
    public AccountNotActivatedException(String message) {
        super(message);
    }
    /**
     * Constructs the exception that raises the message passed in and throws the
     * exception as the throw-able type passed in
     * @param message
     * @param throwable
     */
    public AccountNotActivatedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
