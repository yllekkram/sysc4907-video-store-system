/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Samual
 */
public interface AccountService {
    public List<Account> getAccounts() throws DataAccessException;

	  public Account getAccount(Long accountId) throws DataAccessException;

	 
	  public void registerAccount(Account account) throws DataAccessException;
          
          public Account loginAccount(String Username,String Password) throws AuthenticationException;
          
          public void removeAccount(Long accountID);
}
