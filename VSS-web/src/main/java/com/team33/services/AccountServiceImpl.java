/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import com.team33.entities.dao.AccountDao;
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

    

    @Autowired
    private AccountDao accountDao;
    
    @Transactional
    public void registerAccount(Account account) throws DataAccessException {
         accountDao.saveAccount(account);
    }
    
    @Transactional
    public Account getAccount(Long accountId) throws DataAccessException{
        return accountDao.getAccount(accountId);
    }

    @Transactional
    public List<Account> getAccounts() throws DataAccessException {
        return accountDao.getAccounts();
    }

    @Transactional
    public void removeAccount(Long accountID) {
       accountDao.removeAccount(accountID);
    }
    
}

