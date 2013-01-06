/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.exception;

/**
 *
 * @author Samual
 */
public class AuthenticationException extends Exception{
    public AuthenticationException(String message) {
        super(message);
    }
    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
