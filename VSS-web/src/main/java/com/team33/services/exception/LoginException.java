
package com.team33.services.exception;

/**
 * This exception occurs when the customer provides
 * null login credentials
 * @author Samual
 */
public class LoginException extends Exception {
    
    /**
     * Constructs the exception that raises the message passed in
     * @param msg
     */
    public LoginException(String msg){
        super(msg);
    }
    
}
