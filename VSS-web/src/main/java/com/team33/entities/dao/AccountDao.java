package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.*;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author LaFamiglia
 */
public interface AccountDao {

	  /**
     *
     * @return
     * @throws DataAccessException
     */
    public List<Account> getAccounts() throws DataAccessException;

	  /**
     *
     * @param accountId
     * @return
     * @throws DataAccessException
     */
    public Account getAccount(Integer accountId) throws DataAccessException;

	  /**
     *
     * @param username
     * @return
     * @throws DataAccessException
     */
    public Account getAccount(String username) throws  DataAccessException;
          
	  /**
     *
     * @param account
     * @throws DataAccessException
     */
    public void saveAccount(Account account) throws DataAccessException;
          
          /**
     *
     * @param accountID
     */
    public void removeAccount(Integer accountID);
}
