/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.Account;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test/*.xml"})
public class AccountDaoImplTest {
    
    @Autowired
    private AccountDaoImpl accountDaoImpl;
    
    private Account testAccountNotActivated;
    private Account testAccountActivated;
    
    public AccountDaoImplTest() {
        testAccountNotActivated = new Account(0, "Hello");
        testAccountNotActivated.setPassword("World");
        testAccountNotActivated.setActivated(false);
        
        testAccountActivated = new Account(1, "Hello");
        testAccountActivated.setPassword("World");
        testAccountActivated.setActivated(true);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    public void setAccountDaoImpl(AccountDaoImpl accountDaoImpl){
        this.accountDaoImpl = accountDaoImpl;
        
        this.accountDaoImpl.saveAccount(testAccountNotActivated);
        this.accountDaoImpl.saveAccount(testAccountActivated);
    }
    /**
     * Test of getAccounts method, of class AccountDaoImpl.
     */
    @Test
    @Transactional
    public void testGetAccounts() {
        System.out.println("getAccounts() : List<Account> -> [DataAccessException]");
        
        // Check if return is not null
        assertNotNull(this.accountDaoImpl.getAccounts());
        
        assertEquals(this.accountDaoImpl.getAccounts().size(), 2);
        
        // Check for correct exception thrown
        SessionFactory tmpFactory = this.accountDaoImpl.getSessionFactory();
        this.accountDaoImpl.setSessionFactory(null);
        try{
            this.accountDaoImpl.getAccounts();
            fail("DataAccessException was not thrown");
        }catch(DataAccessException e){
            
        }finally{
            this.accountDaoImpl.setSessionFactory(tmpFactory);
        }
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Transactional
    public void testGetAccount_Integer() {
        System.out.println("getAccount(Integer) : Account -> [DataAccessException]");

        // Check if account was retieved correctly
        Account account = this.accountDaoImpl.getAccount(0);
        assertNotNull(account);
        assertEquals(account.getName(), this.testAccountNotActivated.getName());
        assertEquals(account.getId(), this.testAccountNotActivated.getId());
        assertEquals(account.getPassword(), this.testAccountNotActivated.getPassword());
        assertFalse(account.getActivated());
        
        // Check for negative input
        try{
            account = null;
            account = this.accountDaoImpl.getAccount(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(account);
        }
        
        // Check for positive input but not in system
        try{
            account = null;
            account = this.accountDaoImpl.getAccount(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(account);
        }
        
        // Check for null input
        try{
            account = null;
            account = this.accountDaoImpl.getAccount((Integer)null);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(account);
        }
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Transactional
    public void testGetAccount_String() {
        System.out.println("getAccount(String) : Account -> [DataAccessException]");
        
        // Check if account was retieved correctly
        Account account = this.accountDaoImpl.getAccount("Hello");
        assertNotNull(account);
        assertEquals(account.getName(), this.testAccountNotActivated.getName());
        assertEquals(account.getId(), this.testAccountNotActivated.getId());
        assertEquals(account.getPassword(), this.testAccountNotActivated.getPassword());
        assertFalse(account.getActivated());
        
        // Check for invalid input
        try{
            account = null;
            account = this.accountDaoImpl.getAccount("kajhdfkahdlkh");
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(account);
        }
        
        // Check for null input
        try{
            account = null;
            account = this.accountDaoImpl.getAccount((String)null);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(account);
        }
        
        // Check for empty string input
        try{
            account = null;
            account = this.accountDaoImpl.getAccount("");
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(account);
        }
    }

    /**
     * Test of saveAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testSaveAccount() {
        System.out.println("saveAccount(Account) : Void -> [DataAccessException]");
        
        // Save null account
        try{
            this.accountDaoImpl.saveAccount(null);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            
        }
        
        // Save new Account
        try{
            Account account = new Account(3, "World");
            this.accountDaoImpl.saveAccount(account);
            Account returnAccount = this.accountDaoImpl.getAccount("World");
            assertNotNull(returnAccount);
            assertEquals(account.getId(), returnAccount.getId());
            assertEquals(account.getName(), returnAccount.getName());            
        }catch(DataAccessException e){
            fail("DataAccessException was thrown");
        }
        
        // Save old Account
        try{
            Account account = this.accountDaoImpl.getAccount(0);
            account.setPassword("1234");
            this.accountDaoImpl.saveAccount(account);
            account = this.accountDaoImpl.getAccount(0);
            assertNotNull(account);
            assertEquals(account.getName(), this.testAccountNotActivated.getName());
            assertEquals(account.getId(), this.testAccountNotActivated.getId());
            assertEquals(account.getPassword(), this.testAccountNotActivated.getPassword());
            assertFalse(account.getActivated());
        }catch(DataAccessException e){
            fail("DataAccessException was thrown");
        }
    }

    /**
     * Test of removeAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testRemoveAccount() {
        System.out.println("removeAccount(Integer) : Void -> [DataAccessException]");
        
        // Check if remove is successful
        try{
            this.accountDaoImpl.removeAccount(0);
            List<Account> accounts = this.accountDaoImpl.getAccounts();
            assertEquals(accounts.size(), 1);
            assertEquals(accounts.get(0).getName(), this.testAccountActivated.getName());
            assertEquals(accounts.get(0).getId(), this.testAccountActivated.getId());
            assertEquals(accounts.get(0).getPassword(), this.testAccountActivated.getPassword());
            assertTrue(accounts.get(0).getActivated());
        }catch(DataAccessException e){
            fail("DataAccessException was thrown");
        }
        
        // Check for negative input
        try{
            this.accountDaoImpl.removeAccount(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            
        }
        
        // Check for null input
        try{
            this.accountDaoImpl.removeAccount((Integer)null);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            
        }
        
        // Check for accounts not in db
        try{
            this.accountDaoImpl.removeAccount(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            
        }
    }
}
