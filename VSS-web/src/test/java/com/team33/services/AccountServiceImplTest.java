/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.AccountNotFoundException;
import com.team33.services.exception.AuthenticationException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author Daywalker
 */
public class AccountServiceImplTest {
    
    private AccountServiceImpl accountServiceImpl;
    
    public AccountServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.accountServiceImpl = new AccountServiceImpl();
        this.accountServiceImpl.setAccountDaoImpl(new AccountDaoImplTestStub());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setAccountDAO method, of class AccountServiceImpl.
     */
    @Test
    public void testSetAccountDAO() {
        System.out.println("setAccountDAO");
        assertNotNull("AssertNotNull - Expeceted Output : this.accountServiceImpl.getAccountDao(), Output : " + this.accountServiceImpl.getAccountDaoImpl(), this.accountServiceImpl.getAccountDaoImpl());
        
        this.accountServiceImpl.setAccountDaoImpl(null);
        assertNull("AssertNull - Expected Output : this.accountServiceImpl.getAccountDao(), Ouptput : " + this.accountServiceImpl.getAccountDaoImpl(), this.accountServiceImpl.getAccountDaoImpl());
    }

    /**
     * Test of loginAccount method, of class AccountServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testLoginAccount() throws Exception {
        System.out.println("loginAccount");
        try{
            this.accountServiceImpl.loginAccount(null, null);
            fail("AccountNotFoundException not thrown");
        }catch(AccountNotFoundException e){
            // Suppose to happen
        }
        try{
            this.accountServiceImpl.loginAccount("Test1", "1234");
            fail("AccountNotActiviatedException not thrown");
        }catch(AccountNotActivatedException e){
            // Suppose to happen
        }
        try{
            this.accountServiceImpl.loginAccount("Test1", "23");
            fail("AuthenticationException not thrown");
        }catch(AuthenticationException e){
            //Suppose to happen
        }
        Account a = this.accountServiceImpl.loginAccount("Test1", "1234");
        assertNotNull("AssertNotNull - Expected Output : a != NULL, Output : " + a , a);
        assertEquals("AssertEquals - Expected Output : a.getName() == Test1, Output : " + a.getName(), a.getName().equals("Test1"));
        assertEquals("AssertEquals - Expected Output : a.getPassword() == 1234, Output : " + a.getPassword(), a.getPassword().equals("1234"));
        assertTrue("AssertTrue - Expected Output : a.getActivated() == TRUE, Output : " + a.getActivated(), a.getActivated());
    }

    /**
     * Test of registerAccount method, of class AccountServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testRegisterAccount() {
        System.out.println("registerAccount");
        
        try{
            this.accountServiceImpl.registerAccount(null);
            fail("DataAccessException not thrown");
        }catch(DataAccessException e){
            //Suppose to happen
        }
        
        Account testAccount = new Account(0, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountServiceImpl.registerAccount(testAccount);
        }catch(DataAccessException e){
            fail("Exception Thrown : " + e.getLocalizedMessage());
        }
        Account account = null;
        try{
            account = this.accountServiceImpl.getAccount(0);
        }catch(DataAccessException e){
            fail("Exception Thrown (Data in Test): " + e.getLocalizedMessage());            
        }
        assertNotNull("AssertNotNull - Expected Output : account != NULL, Output : " + account, account);
        assertTrue("AssertTrue - Expected Output : account.getId() == 15, Output : " + account.getId(), account.getId() == 0);
        assertTrue("AssertTrue - Expected Output : account.getName() == Test1, Output : " + account.getName(), account.getName().equals("Test1"));
        assertTrue("AssertTrue - Expected Output : account.getPassword() == 1234, Output : " + account.getPassword(), account.getPassword().equals("1234"));
    }

    /**
     * Test of getAccount method, of class AccountServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testGetAccount() {
        System.out.println("getAccount");
        
        try{
            this.accountServiceImpl.getAccount(0);
            fail("DataAccessException was not thrown");
        }catch(DataAccessException e){
            // Suppose to happen
        }
        
        Account testAccount = new Account(15, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountServiceImpl.registerAccount(testAccount);
        }catch(DataAccessException e){
            Assume.assumeNoException(e);
        }

        Account account = null;
        try{
            account = this.accountServiceImpl.getAccount(15);
        }catch(DataAccessException e){
            fail("Exception Thrown (Data in Test): " + e.getLocalizedMessage());
        }
        assertNotNull("AssertNotNull - Expected Output : account != NULL, Output : " + account, account);
        assertTrue("AssertTrue - Expected Output : account.getId() == 15, Output : " + account.getId(), account.getId() == 15);
        assertTrue("AssertTrue - Expected Output : account.getName() == Test1, Output : " + account.getName(), account.getName().equals("Test1"));
        assertTrue("AssertTrue - Expected Output : account.getPassword() == 1234, Output : " + account.getPassword(), account.getPassword().equals("1234"));
    }

    /**
     * Test of getAccounts method, of class AccountServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testGetAccounts() {
        System.out.println("getAccounts");
        List<Account> expResult = null;
        try{
            expResult = this.accountServiceImpl.getAccounts();  
        }catch(DataAccessException e){
            fail("Exception Thrown (Empty Test): " + e.getLocalizedMessage());
        }
        
        assertNotNull("AssertNotNull - Expected Output : expResult != NULL, Output : " + expResult, expResult);
        assertTrue("AssertTrue - Expected Output : expResult.isEmpty() == TRUE, Output : " + expResult.isEmpty(), expResult.isEmpty());
        
        Account testAccount = new Account(0, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountServiceImpl.registerAccount(testAccount);
        }catch(DataAccessException e){
            Assume.assumeNoException(e);
        }
        
        try{
            expResult = this.accountServiceImpl.getAccounts();
        }catch(DataAccessException e){
            fail("Exception Thrown (Data in Test): " + e.getLocalizedMessage());
        }
        assertTrue("AssertTrue - Expected Output : expResult.size() == 1, Output : " + expResult.size(), expResult.size() == 1);
        Account account = expResult.get(0);
        assertNotNull("AssertNotNull - Expected Output : account != NULL, Output : " + account, account);
        assertTrue("AssertTrue - Expected Output : account.getId() == 0, Output : " + account.getId(), account.getId() == 0);
        assertTrue("AssertTrue - Expected Output : account.getName() == Test1, Output : " + account.getName(), account.getName().equals("Test1"));
        assertTrue("AssertTrue - Expected Output : account.getPassword() == 1234, Output : " + account.getPassword(), account.getPassword().equals("1234"));
    }

    /**
     * Test of removeAccount method, of class AccountServiceImpl.
     */
    @Test
    public void testRemoveAccount() {
        System.out.println("removeAccount");
        try{
            this.accountServiceImpl.removeAccount(0);
            fail("Error thrown while removing non-exsisting account id");
        }catch(DataAccessException e){
            // Suppose to happen
        }
        
        Account testAccount = new Account(0, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountServiceImpl.registerAccount(testAccount);
        }catch(DataAccessException e){
            Assume.assumeNoException(e);
        }
        
        try{
            this.accountServiceImpl.removeAccount(testAccount.getId());
        }catch(DataAccessException e){
            fail("Exception Thrown : " + e.getLocalizedMessage());
        }
        
        List<Account> expResult = null;
        try{
            expResult = this.accountServiceImpl.getAccounts();  
        }catch(DataAccessException e){
            fail("Exception Thrown (Empty Test): " + e.getLocalizedMessage());
        }
        
        assertNotNull("AssertNotNull - Expected Output : expResult != NULL, Output : " + expResult, expResult);
        assertTrue("AssertTrue - Expected Output : expResult.size() == 0, Output : " + expResult.size(), expResult.isEmpty());
    }
}
