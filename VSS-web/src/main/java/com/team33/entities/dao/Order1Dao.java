package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.*;
import com.team33.services.exception.*;
import org.springframework.dao.DataAccessException;

public interface Order1Dao {
	
	  public List<Order1> getOrders(Integer accountId,Boolean accountActivated) throws DataAccessException,AccountNotActivatedException,LoginException;

	  public Order1 getOrder(Long orderId) throws DataAccessException,AccountNotActivatedException,LoginException;

	 
	  public void saveOrder(Order1 order) throws DataAccessException;
          
          public void removeOrder(Integer orderId);
          public void removeOrders(Integer accountID,Boolean accountActivated) throws  AccountNotActivatedException,LoginException;
}
