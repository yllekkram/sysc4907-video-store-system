package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.*;
import com.team33.services.exception.*;
import org.springframework.dao.DataAccessException;

public interface Order1Dao {
	
    /*Return all orders associated with an account*/
	  public List<Order1> getOrders(LoginToken loginToken) throws DataAccessException,AccountNotActivatedException;

	  public Order1 getOrder(Integer orderId,LoginToken loginToken) throws DataAccessException,AccountNotActivatedException;

	 
	  public void saveOrder(Order1 order,LoginToken loginToken) throws DataAccessException,AccountNotActivatedException;
          
          /*An order is attached to a particular account at creation
           so remove order only needs the orderId to remove an order from an account*/
          public void removeOrder(Integer orderId,LoginToken loginToken) throws AccountNotActivatedException;
          
}
