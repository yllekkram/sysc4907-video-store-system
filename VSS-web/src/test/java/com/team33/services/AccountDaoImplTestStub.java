/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import com.team33.entities.dao.AccountDao;
import com.team33.entities.dao.AccountDaoImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;

/**
 *
 * @author Daywalker
 */
public class AccountDaoImplTestStub extends AccountDaoImpl{

    @Override
    public List<Account> getAccounts() throws DataAccessException {
        return new ArrayList<Account>();
    }

    @Override
    public Account getAccount(Integer accountId) throws DataAccessException {
        Account a = null;
        if (accountId == 0L){
            a = new Account(0, "Test1");
            a.setPassword("1234");
        }else if (accountId == 15){
            return null;
        }else if (accountId == 51){
            throw new DataRetrievalFailureException("Stub getAccount(Long)");
        }else{
            return null;
        }
        
        return a;
    }

    @Override
    public Account getAccount(String username) throws DataAccessException {
        Account a = null;
        if (username == null){
            return null;
        }else if (username.equals("Test1")){
            a = new Account(0, "Test1");
            a.setPassword("1234");
        }else if (username.equals("Test2")){
            return null;
        }else{
            throw new DataRetrievalFailureException("Stub getAccount(String)");
        }
        return a;
    }

    @Override
    public void saveAccount(Account account) throws DataAccessException {
        if (account == null){
            throw new DataAccessResourceFailureException("Stub saveAccount(Account)");
        }else if (account.getName().equals("Test1")){
            // Completes
        }
    }

    @Override
    public void removeAccount(Integer accountID) {
        if (accountID != 0L){
            throw new DataAccessResourceFailureException("Stub removeAccount(Account)");
        }
    }
    
}
