package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.*;
import org.springframework.dao.DataAccessException;

public interface AccountDao {

	  public List<Account> getAccounts() throws DataAccessException;

	  public Account getAccount(Integer accountId) throws DataAccessException;

	  public Account getAccount(String username) throws  DataAccessException;
          
	  public void saveAccount(Account account) throws DataAccessException;
          
          public void removeAccount(Integer accountID);
}
