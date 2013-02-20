/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import com.team33.entities.dao.AccountDaoImpl;
import com.team33.services.exception.DataAccessException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daywalker
 */
public class AccountDaoImplTestStub extends AccountDaoImpl{
    
    private ArrayList<Account> dummyAccount;
    private Account testAccountNotActivated;
    private Account testAccountActivated;
    
    public AccountDaoImplTestStub(){
        this.dummyAccount = new ArrayList<Account>();
        setDummyAccountInfo();
    }
    public final void setDummyAccountInfo(){
        testAccountNotActivated = new Account(0, "Hello");
        testAccountNotActivated.setPassword("World");
        testAccountNotActivated.setActivated(false);

        testAccountActivated = new Account(1, "Hello");
        testAccountActivated.setPassword("World");
        testAccountActivated.setActivated(true);
    }
    
    public void addDummyAccount(Account account){
        this.dummyAccount.add(account);
    }
    
    public void clearDummyAccounts(){
        this.dummyAccount.clear();
        setDummyAccountInfo();
    }
    
    @Override
    public List<Account> getAccounts() throws DataAccessException{
        return dummyAccount;
    }
    
    @Override
    public Account getAccount(Integer accountId) throws DataAccessException{
        if (accountId == null){
            throw new DataAccessException("Dummy Message");
        }else if (accountId.intValue() == -1){
            throw new DataAccessException("Dummy Message");
        }else if (accountId.intValue() == 9999){
            throw new DataAccessException("Dummy Message");
        }
        if (accountId.intValue() == 0){
            this.dummyAccount.add(testAccountNotActivated);
            return testAccountNotActivated;
        }else if (accountId.intValue() == 1){
            this.dummyAccount.add(testAccountActivated);
            return testAccountActivated;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public Account getAccount(String username) throws DataAccessException{
        if (username == null){
            throw new DataAccessException("Dummy Message");
        }else if (username.equals("")){
            throw new DataAccessException("Dummy Message");
        }else if (username.equals("kajhdfkahdlkh")){
            throw new DataAccessException("Dummy Message");
        }else if (username.equals("Hello")){
            return testAccountNotActivated;
        }else if (username.equals("World")){
            return testAccountActivated;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void saveAccount(Account account) throws DataAccessException{
        if (account == null){
            throw new DataAccessException("Dummy Message");
        }else if (account.getName().equals("World")){
            
        }else if (account.getName().equals("Hello")){
        
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void removeAccount(Integer accountId) throws DataAccessException{
        if (accountId == null){
            throw new DataAccessException("Dummy Message");
        }else if (accountId.intValue() == -1){
            throw new DataAccessException("Dummy Message");
        }else if (accountId.intValue() == 9999){
            throw new DataAccessException("Dummy Message");
        }
        if (accountId.intValue() == 0){
            this.dummyAccount.remove(testAccountNotActivated);
        }else if (accountId.intValue() == 1){
            this.dummyAccount.remove(testAccountActivated);
        }
        throw new DataAccessException("Should not reach here");
    }
}
