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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Caleb
 */
@ContextConfiguration(locations = {"/WEB-INF/applicationContext-test.xml"})
public class AccountDaoImplTest {
    
    @Autowired
    private AccountDaoImpl accountDao;
    
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

    public void setAccountDaoImpl(AccountDaoImpl accountDao){
        this.accountDao = accountDao;
    }
    /**
     * Test of setSessionFactory method, of class AccountDaoImpl.
     */
    @Test
    public void testSetSessionFactory() {
        System.out.println("setSessionFactory");
        SessionFactory sessionFactory = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        instance.setSessionFactory(sessionFactory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccounts method, of class AccountDaoImpl.
     */
    @Test
    public void testGetAccounts() {
        System.out.println("getAccounts");
        AccountDaoImpl instance = new AccountDaoImpl();
        List expResult = null;
        List result = instance.getAccounts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testGetAccount_Long() {
        System.out.println("getAccount");
        Long accountId = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
        Account result = instance.getAccount(accountId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testGetAccount_String() {
        System.out.println("getAccount");
        String username = "";
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
        Account result = instance.getAccount(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testSaveAccount() {
        System.out.println("saveAccount");
        Account account = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        instance.saveAccount(account);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAccount method, of class AccountDaoImpl.
     */
    @Test
    public void testRemoveAccount() {
        System.out.println("removeAccount");
        Long accountId = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        instance.removeAccount(accountId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
