/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import com.team33.entities.Orders;
import com.team33.services.exception.*;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * This interface dictates what features are relevant to account services
 *
 * @author Samual
 */
public interface AccountService {

    /**
     * Get all accounts in the system -- mostly a potential testing feature
     *
     * @return List<Account>
     * @throws DataAccessException
     */
    public List<Account> getAccounts() throws DataAccessException;

    /**
     * Gets an account
     *
     * @param accountId
     * @return Account
     * @throws DataAccessException
     */
    public Account getAccount(Integer accountId) throws DataAccessException;
    
    /**
     * Gets an account using its login token.
     * 
     * @param loginTokenId The id of the Account's LoginToken
     * @return the account with the given LoginToken
     * @throws DataAccess Exception
     * @author Mark
     */
    public Account getAccountByLoginToken(Integer loginTokenId) throws DataAccessException;

    /**
     * Registers account in a system
     *
     * @param username
     * @param Password
     * @throws RegistrationException
     */
    public void registerAccount(String username, String Password) throws RegistrationException;

    /**
     * Logins an account to the system
     *
     * @param Username
     * @param Password
     * @return
     * @throws AuthenticationException
     * @throws AccountNotActivatedException
     * @throws AccountNotFoundException
     * @throws LoginException
     */
    public int loginAccount(String Username, String Password) throws AuthenticationException, AccountNotActivatedException, AccountNotFoundException, LoginException;

    /**
     * Removes an account from the system
     *
     * @param accountID
     */
    public void removeAccount(Integer accountID);

    /**
     *
     * @param accountId
     * @param order
     * @throws AccountNotActivatedException
     */
    public void addOrder(Integer accountId, Orders order) throws AccountNotActivatedException;

    /**
     * Removes an order in the system
     *
     * @param accountId
     * @param order
     */
    public void removeOrder(Integer accountId, Orders order);
}
