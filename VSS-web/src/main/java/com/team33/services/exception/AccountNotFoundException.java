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
    public AccountNotFoundException(String message) {
        super(message);
    }
    public AccountNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
