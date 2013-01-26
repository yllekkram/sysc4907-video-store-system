/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Order1;
import com.team33.services.exception.*;
import java.util.List;

/**
 *
 * @author Samual
 */
public interface OrderService {
    public List<Order1> getOrders(Integer orderId) throws DataAccessException,AccountNotActivatedException;

	  public Order1 getOrder(Integer orderId) throws DataAccessException,AccountNotActivatedException;

	 
	  public void addOrder(Order1 order,Integer accountId) throws DataAccessException;
          
          public void confirmPayment(Integer orderId) throws  AccountNotActivatedException;
          /*Removes all orders attached to a specific account*/
          public void removeOrder(Integer orderID) throws AccountNotActivatedException;
          
}
