/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.exception;

/**
 *
 * @author Samual
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException(String msg) {
        super(msg);
    }
}
