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

    /**
     *
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public List<Orders> getOrders(int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     *
     * @param orderId
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public Orders getOrder(Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public void addPurchase(Integer videoInfoId, Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @param rentalExpiryDate
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public void addRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws DataAccessException, AccountNotActivatedException;

    /**
     *
     * @param orderId
     * @param uuid
     * @param validationNum
     * @param totalCost
     * @throws AccountNotActivatedException
     * @throws PaymentException
     * @throws InsufficientFundsException
     */
    public void confirmPayment(Integer orderId, int uuid, int validationNum, int totalCost) throws AccountNotActivatedException, PaymentException, InsufficientFundsException;
    /*
     * Removes an order attached to a specific account
     */

    /**
     *
     * @param orderID
     * @param uuid
     * @throws AccountNotActivatedException
     */
    public void removeOrder(Integer orderID, int uuid) throws AccountNotActivatedException;

    /**
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @throws AccountNotActivatedException
     */
    public void removePurchase(Integer videoInfoId, Integer orderId, int uuid) throws AccountNotActivatedException;

    /**
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @param rentalExpiryDate
     * @throws AccountNotActivatedException
     */
    public void removeRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws AccountNotActivatedException;
}
