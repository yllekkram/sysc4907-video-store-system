/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.exception;

/**
 *
 * @author Samual
 */
public class AccountNotFoundException extends Exception{
    /**
     *
     * @param message
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
    /**
     *
     * @param message
     * @param throwable
     */
    public AccountNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
