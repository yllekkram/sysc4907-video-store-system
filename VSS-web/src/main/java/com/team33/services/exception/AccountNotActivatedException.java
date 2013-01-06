/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.exception;

/**
 *
 * @author Samual
 */
public class AccountNotActivatedException extends Exception{
    public AccountNotActivatedException(String message) {
        super(message);
    }
    public AccountNotActivatedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
