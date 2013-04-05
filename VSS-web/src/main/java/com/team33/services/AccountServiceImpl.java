package com.team33.services;

import com.team33.entities.Account;
import com.team33.entities.LoginToken;
import com.team33.entities.LoginTokenPK;
import com.team33.entities.Orders;
import com.team33.entities.dao.AccountDao;
import com.team33.services.exception.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides all services related to accounts
 *
 * @author Samual
 */
@Service
public class AccountServiceImpl implements AccountService {

    //tells Spring to inject the dependency
    //be sure to include setter method
    @Autowired
    private AccountDao accountDao;
    private List<Orders> orders;

    /**
     * Sets the current implemented account dao
     *
     * @param dao
     */
    public void setAccountDao(AccountDao dao) {
        this.accountDao = dao;
    }

    /**
     * Retrieves the current implemented account dao
     *
     * @return AccountDao
     */
    public AccountDao getAccountDao() {
        return this.accountDao;
    }

    /**
     * Set the orders for a given account
     *
     * @param List<Orders> orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     * Retrieves all orders for an account
     *
     * @return List<Orders>
     */
    public List<Orders> getOrders() {
        return this.orders;
    }

    /**
     * Handles the business logic for account login
     *
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     * @throws AccountNotFoundException
     * @throws AccountNotActivatedException
     * @throws LoginException
     */
    @Transactional
    @Override
    public int loginAccount(String username, String password) throws AuthenticationException, AccountNotFoundException, AccountNotActivatedException, LoginException {
        if (username == null || username.equals("")
                || password == null || password.equals("")) {
            throw new LoginException("Invalid login info!");
        }
        Account account = this.accountDao.getAccount(username);
        if (account == null) {
            throw new AccountNotFoundException("Account was not found");
        }
        if (account.getActivated() != Boolean.TRUE) {
            throw new AccountNotActivatedException("Account not activated!");
        } else {
            if (account.getName().matches(username)) {
                if (!account.getPassword().matches(password)) {
                    throw new AuthenticationException("Unable to authenticate the account");
                }
            }
        }
        return this.accountDao.getLoginToken(account.getId()).getLogintokenPK().getId();
    }

    /**
     * Handles the business logic for account registration
     *
     * @param username
     * @param password
     * @throws RegistrationException
     */
    @Transactional(rollbackFor = RegistrationException.class)
    @Override
    public void registerAccount(String username, String password) throws RegistrationException {
        //if username already exists in system throw exception
        if (this.getAccountDao().getAccount(username) != null) {
            throw new RegistrationException("Username already exists, please try another.");
        }
        Account acc = new Account();
        acc.setName(username);
        acc.setPassword(password);
        try {
            this.getAccountDao().saveAccount(acc);

            LoginToken token = new LoginToken(new LoginTokenPK());
            token.getLogintokenPK().setAccountid(acc.getId());
            token.setAccount(acc);
            this.getAccountDao().saveLoginToken(token);
        } catch (com.team33.services.exception.DataAccessException e) {
            throw new RegistrationException("Registration Failed");
        }
    }

    /**
     * Retrieves a specific account given its id
     *
     * @param accountId
     * @return Account
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public Account getAccount(Integer accountId) throws DataAccessException {
        return accountDao.getAccount(accountId);
    }

    @Override
    @Transactional
    public Account getAccountByLoginToken(Integer loginTokenId) {
        return accountDao.getAccountByLoginToken(loginTokenId);
    }

    /**
     * retrieves all accounts in the system
     *
     * @return List<Accounts>
     * @throws DataAccessException
     */
    @Transactional
    @Override
    public List<Account> getAccounts() throws DataAccessException {
        return accountDao.getAccounts();
    }

    /**
     * Removes an account with accountId
     *
     * @param accountID
     */
    @Transactional
    @Override
    public void removeAccount(Integer accountID) {
        accountDao.removeAccount(accountID);
    }

    /**
     * Will add an order to the account only if it is active
     *
     * @param accountId
     * @param order
     * @throws AccountNotActivatedException
     */
    @Override
    @Transactional
    public void addOrder(Integer accountId, Orders order) throws AccountNotActivatedException {
        if (this.getAccount(accountId).getActivated()) {
            //Is the order already tied to the account?
            if (!this.getOrders().contains(order)) {
                this.getOrders().add(order);
            }
        }
        throw new AccountNotActivatedException("Please activate the account before ordering videos");
    }

    /**
     * Removes an order from the account with accountId
     *
     * @param accountId
     * @param order
     */
    @Override
    @Transactional
    public void removeOrder(Integer accountId, Orders order) {
        if (this.getOrders().contains(order)) {
            this.getOrders().remove(order);
        }
    }
}
