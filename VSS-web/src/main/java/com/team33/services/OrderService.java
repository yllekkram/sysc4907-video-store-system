/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Orders;
import com.team33.services.exception.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Samual
 */
public interface OrderService {

    public List<Orders> getOrders(int uuid) throws DataAccessException, AccountNotActivatedException;

    public Orders getOrder(Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException;

    public void addPurchase(Integer videoInfoId, Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException;

    public void addRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws DataAccessException, AccountNotActivatedException;

    public void confirmPayment(Integer orderId, int uuid, int validationNum, int totalCost) throws AccountNotActivatedException, PaymentException, InsufficientFundsException;
    /*
     * Removes an order attached to a specific account
     */

    public void removeOrder(Integer orderID, int uuid) throws AccountNotActivatedException;

    public void removePurchase(Integer videoInfoId, Integer orderId, int uuid) throws AccountNotActivatedException;

    public void removeRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws AccountNotActivatedException;
}
