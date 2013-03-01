/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.services.exception.AuthenticationException;
import com.team33.entities.Account;
import com.team33.entities.Orders;
import com.team33.services.exception.*;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Samual
 */
public interface AccountService {

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
     * @param Password
     * @throws RegistrationException
     */
    public void registerAccount(String username, String Password) throws RegistrationException;

    /**
     *
     * @param Username
     * @param Password
     * @return
     * @throws AuthenticationException
     * @throws AccountNotActivatedException
     * @throws AccountNotFoundException
     * @throws LoginException
     */
    public Account loginAccount(String Username, String Password) throws AuthenticationException, AccountNotActivatedException, AccountNotFoundException, LoginException;

    /**
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
     *
     * @param accountId
     * @param order
     */
    public void removeOrder(Integer accountId, Orders order);
}
