package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.*;
import org.springframework.dao.DataAccessException;

public interface Order1Dao {
	
	  public List<Order1> getOrders() throws DataAccessException;

	  public Order1 getOrder(Long orderId) throws DataAccessException;

	 
	  public void saveOrder(Order1 order) throws DataAccessException;
}
