package com.team33.services.exception;

/**
 * This exception is raised for invalid(i.e. the username provided is taken)
 * username and password pairs during registration
 *
 * @author Samual
 */
public class RegistrationException extends Exception {

    /**
     * Constructs the exception that raises the message passed in
     *
     * @param msg
     */
    public RegistrationException(String msg) {
        super(msg);
    }
}
