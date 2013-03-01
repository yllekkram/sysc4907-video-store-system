
package com.team33.services.exception;

/**
 *This exception occurs when provided username and password parameters cannot
 * be authenticated 
 * @author Samual
 */
public class AuthenticationException extends Exception{
    /**
     * Constructs the exception that raises the message passed in
     * @param message
     */
    public AuthenticationException(String message) {
        super(message);
    }
    /**
     * Constructs the exception that raises the message passed in
     * and throws the exception as the throw-able type passed in
     * @param message
     * @param throwable
     */
    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
