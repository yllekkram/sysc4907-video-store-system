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
import org.junit.Assume;
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
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext-test.xml"})
public class AccountDaoImplTest {
    
    @Autowired
    private AccountDaoImpl accountDaoImpl;
    
    public AccountDaoImplTest() {
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
    }
    /**
     * Test of setSessionFactory method, of class AccountDaoImpl.
     */
    @Test
    public void testSetSessionFactory() {
        System.out.println("setSessionFactory");
        AccountDaoImpl instance = new AccountDaoImpl();
        
        SessionFactory sessionFactory = null;
        instance.setSessionFactory(sessionFactory);
        assertNull("AssertNull - Expected Output : instance.getSessionFactory() == NULL, Output : " + instance.getSessionFactory(), instance.getSessionFactory());
        
        assertNotNull("AssertNotNull - Expected Output : this.accountDaoImpl != NULL, Output : " + this.accountDaoImpl, this.accountDaoImpl);
        
        assertNotNull("AssertTrue - Expected Output : this.accountDaoImpl.getSessionFactory() != NULL, Output : " + this.accountDaoImpl.getSessionFactory(), this.accountDaoImpl.getSessionFactory());
    }

    /**
     * Test of getAccounts method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccounts() {
        System.out.println("getAccounts");
        List<Account> expResult = null;
        try{
            expResult = this.accountDaoImpl.getAccounts();  
        }catch(DataAccessException e){
            fail("Exception Thrown (Empty Test): " + e.getLocalizedMessage());
        }
        
        assertNotNull("AssertNotNull - Expected Output : expResult != NULL, Output : " + expResult, expResult);
        assertTrue("AssertTrue - Expected Output : expResult.size() == 0, Output : " + expResult.size(), expResult.isEmpty());
        
        Account testAccount = new Account(0, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountDaoImpl.saveAccount(testAccount);
        }catch(DataAccessException e){
            Assume.assumeNoException(e);
        }
        
        try{
            expResult = this.accountDaoImpl.getAccounts();
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
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_Long() {
        System.out.println("getAccount");
        
        Account testAccount = new Account(15, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountDaoImpl.saveAccount(testAccount);
        }catch(DataAccessException e){
            Assume.assumeNoException(e);
        }

        Account account = null;
        try{
            account = this.accountDaoImpl.getAccount(15L);
        }catch(DataAccessException e){
            fail("Exception Thrown (Data in Test): " + e.getLocalizedMessage());
        }
        assertNotNull("AssertNotNull - Expected Output : account != NULL, Output : " + account, account);
        assertTrue("AssertTrue - Expected Output : account.getId() == 15, Output : " + account.getId(), account.getId() == 15);
        assertTrue("AssertTrue - Expected Output : account.getName() == Test1, Output : " + account.getName(), account.getName().equals("Test1"));
        assertTrue("AssertTrue - Expected Output : account.getPassword() == 1234, Output : " + account.getPassword(), account.getPassword().equals("1234"));
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_String() {
        System.out.println("getAccount");
        
        Account testAccount = new Account(0, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountDaoImpl.saveAccount(testAccount);
        }catch(DataAccessException e){
            Assume.assumeNoException(e);
        }

        Account account = null;
        try{
            account = this.accountDaoImpl.getAccount("Test1");
        }catch(DataAccessException e){
            fail("Exception Thrown (Data in Test): " + e.getLocalizedMessage());
        }
        assertNotNull("AssertNotNull - Expected Output : account != NULL, Output : " + account, account);
        assertTrue("AssertTrue - Expected Output : account.getId() == 15, Output : " + account.getId(), account.getId() == 0);
        assertTrue("AssertTrue - Expected Output : account.getName() == Test1, Output : " + account.getName(), account.getName().equals("Test1"));
        assertTrue("AssertTrue - Expected Output : account.getPassword() == 1234, Output : " + account.getPassword(), account.getPassword().equals("1234"));
    }

    /**
     * Test of saveAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testSaveAccount() {
        System.out.println("saveAccount");
        Account testAccount = new Account(0, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountDaoImpl.saveAccount(testAccount);
        }catch(DataAccessException e){
            fail("Exception Thrown : " + e.getLocalizedMessage());
        }
        Account account = null;
        try{
            account = this.accountDaoImpl.getAccount("Test1");
        }catch(DataAccessException e){
            fail("Exception Thrown (Data in Test): " + e.getLocalizedMessage());
        }
        assertNotNull("AssertNotNull - Expected Output : account != NULL, Output : " + account, account);
        assertTrue("AssertTrue - Expected Output : account.getId() == 15, Output : " + account.getId(), account.getId() == 0);
        assertTrue("AssertTrue - Expected Output : account.getName() == Test1, Output : " + account.getName(), account.getName().equals("Test1"));
        assertTrue("AssertTrue - Expected Output : account.getPassword() == 1234, Output : " + account.getPassword(), account.getPassword().equals("1234"));
    }

    /**
     * Test of removeAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testRemoveAccount() {
        System.out.println("removeAccount");
        
        Account testAccount = new Account(0, "Test1");
        testAccount.setPassword("1234");
        try{
            this.accountDaoImpl.saveAccount(testAccount);
        }catch(DataAccessException e){
            Assume.assumeNoException(e);
        }
        
        try{
            this.accountDaoImpl.removeAccount((long)testAccount.getId());
        }catch(DataAccessException e){
            fail("Exception Thrown : " + e.getLocalizedMessage());
        }
        
        List<Account> expResult = null;
        try{
            expResult = this.accountDaoImpl.getAccounts();  
        }catch(DataAccessException e){
            fail("Exception Thrown (Empty Test): " + e.getLocalizedMessage());
        }
        
        assertNotNull("AssertNotNull - Expected Output : expResult != NULL, Output : " + expResult, expResult);
        assertTrue("AssertTrue - Expected Output : expResult.size() == 0, Output : " + expResult.size(), expResult.isEmpty());
    }
}
