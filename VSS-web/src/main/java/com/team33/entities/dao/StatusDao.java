package com.team33.entities.dao;


import com.team33.entities.*;
import org.springframework.dao.DataAccessException;

public interface StatusDao {

	

	  public Status getAccountStatus(Long statusId) throws DataAccessException;

	 
	  public void saveStatus(Status status) throws DataAccessException;
}
