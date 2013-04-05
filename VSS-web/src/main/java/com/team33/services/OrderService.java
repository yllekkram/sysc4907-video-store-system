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
 * Dictates the features responsible for order processing
 *
 * @author Samual
 */
public interface OrderService {

    /**
     * Retrieves all orders for an account
     *
     * @param uuid
     * @return List<Orders>
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public List<Orders> getOrders(int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     * Retrieves a single order for an account
     *
     * @param orderId
     * @param uuid
     * @return Orders
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public Orders getOrder(Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     * Adds a purchase to an order
     *
     * @param videoInfoId
     * @param order
     * @param uuid
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public void addPurchase(Integer videoInfoId, Orders order, int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     * Adds a rental to an order
     *
     * @param videoInfoId
     * @param order
     * @param uuid
     * @param rentalExpiryDate
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public void addRental(Integer videoInfoId, Orders order, int uuid, Date rentalExpiryDate) throws DataAccessException, AccountNotActivatedException;

    /**
     * Confirms payment of an order
     *
     * @param order
     * @param uuid
     * @param validationNum
     * @param totalCost
     * @throws AccountNotActivatedException
     * @throws PaymentException
     * @throws InsufficientFundsException
     */
    public void confirmPayment(Orders order, int uuid, int validationNum, int totalCost) throws AccountNotActivatedException, PaymentException, InsufficientFundsException;
    /*
     * Removes an order attached to a specific account
     */

    /**
     * Removes an order on an account
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
     * Remove a rental from an order
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @param rentalExpiryDate
     * @throws AccountNotActivatedException
     */
    public void removeRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws AccountNotActivatedException;
    
    /**
     * Save an order
     * 
     * @author Mark
     * @param order The order that will be saved to the database.
     */
    public void saveOrder(Orders order);
    
    /**
     * Save a new order or update an existing order
     * 
     * @author Mark
     * @param order The order that will be saved to the database.
     */
    public void saveOrUpdateOrder(Orders order);
    
    public boolean validateOrder(int videoId, int orderId, int loginTokenId) throws AccountNotActivatedException, DataAccessException;
}
