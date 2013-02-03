/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.Order1;
import com.team33.entities.Purchase;
import com.team33.entities.Rental;
import com.team33.services.exception.*;
import java.util.List;

/**
 *
 * @author Samual
 */
public interface OrderService {

    public List<Order1> getOrders(LoginToken loginToken) throws DataAccessException, AccountNotActivatedException;

    public Order1 getOrder(Integer orderId, LoginToken loginToken) throws DataAccessException, AccountNotActivatedException;

    public void addPurchase(Purchase purchase, Order1 order, LoginToken loginToken) throws DataAccessException, AccountNotActivatedException;

    public void addRental(Rental rental, Order1 order, LoginToken loginToken) throws DataAccessException, AccountNotActivatedException;

    public void confirmPayment(Integer orderId, LoginToken loginToken, int validationNum, int totalCost) throws AccountNotActivatedException, PaymentException, InsufficientFundsException;
    /*
     * Removes an order attached to a specific account
     */

    public void removeOrder(Integer orderID, LoginToken loginToken) throws AccountNotActivatedException;

    public void removePurchase(Purchase purchase, Integer orderID, LoginToken loginToken) throws AccountNotActivatedException;

    public void removeRental(Rental rental, Integer orderID, LoginToken loginToken) throws AccountNotActivatedException;
}
