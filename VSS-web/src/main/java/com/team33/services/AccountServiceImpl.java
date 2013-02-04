/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import com.team33.entities.Order1;
import com.team33.entities.dao.AccountDaoImpl;
import com.team33.services.exception.*;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Samual
 */
@Service
public class AccountServiceImpl implements AccountService {

    //tells Spring to inject the dependency
    //be sure to include setter method
    @Autowired
    private AccountDaoImpl accountDaoImpl;
    @Autowired
    private List<Order1> orders;
  

    public void setAccountDaoImpl(AccountDaoImpl dao) {
        this.accountDaoImpl = dao;
    }

    public AccountDaoImpl getAccountDaoImpl() {
        return this.accountDaoImpl;
    }

    public void setOrders(List<Order1> orders) {
        this.orders = orders;
    }

    public List<Order1> getOrders() {
        return this.orders;
    }

    @Transactional
    @Override
    public Account loginAccount(String username, String password) throws AuthenticationException, AccountNotFoundException, AccountNotActivatedException, LoginException {
        if (username == null || username.equals("")
                || password == null || password.equals("")) {
            throw new LoginException("Invalid login info!");
        }
        Account account = this.accountDaoImpl.getAccount(username);
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
        return account;
    }

    @Transactional
    @Override
    public void registerAccount(Account account) throws DataAccessException {
        accountDaoImpl.saveAccount(account);
    }

    @Transactional
    @Override
    public Account getAccount(Integer accountId) throws DataAccessException {
        return accountDaoImpl.getAccount(accountId);
    }

    @Transactional
    @Override
    public List<Account> getAccounts() throws DataAccessException {
        return accountDaoImpl.getAccounts();
    }

    @Transactional
    @Override
    public void removeAccount(Integer accountID) {
        accountDaoImpl.removeAccount(accountID);
    }

    @Override
    // Will add an order to the account only if it is active
    public void addOrder(Integer accountId, Order1 order) throws AccountNotActivatedException {
        if (this.getAccount(accountId).getActivated()) {
            //Is the order already tied to the account?
            if (!this.getOrders().contains(order)) {
                this.getOrders().add(order);
            }
        }
        throw new AccountNotActivatedException("Please activate the account before ordering videos");
    }

    @Override
    public void removeOrder(Integer accountId, Order1 order) {
        if (this.getOrders().contains(order)) {
            this.getOrders().remove(order);
        }
    }
}
