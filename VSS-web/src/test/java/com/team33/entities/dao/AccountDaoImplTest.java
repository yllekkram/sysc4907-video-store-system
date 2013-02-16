/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.Account;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/dao/dao-test.xml"})
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

    public void setAccountDao(AccountDao accountDao) {
        this.accountDaoImpl = accountDaoImpl;

        //this.accountDaoImpl.saveAccount(testAccountNotActivated);
        //this.accountDaoImpl.saveAccount(testAccountActivated);
    }

    /**
     * Test of getAccounts method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccounts_NotNull() {
        assertNotNull(this.accountDaoImpl.getAccounts());
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccounts_NotNegSize() {
        assertTrue(this.accountDaoImpl.getAccounts().size() >= 0);
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_Integer_NegId() {
        Account account = null;
        // Check for negative input
        try {
            account = this.accountDaoImpl.getAccount(-1);
            fail("Failed to throw DataAccessException for -1");
        } catch (DataAccessException e) {
            assertNull(account);
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_Integer_InvalidId() {
        Account account = null;
        try {
            account = null;
            account = this.accountDaoImpl.getAccount(9999);
            fail("Failed to throw DataAccessException for 9999");
        } catch (DataAccessException e) {
            assertNull(account);
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_Integer_NullId() {
        Account account = null;
        // Check for null input
        try {
            account = null;
            account = this.accountDaoImpl.getAccount((Integer) null);
            fail("Failed to throw DataAccessException for null");
        } catch (DataAccessException e) {
            assertNull(account);
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_Integer_ValidId() {
        Account account = null;
        try {
            account = null;
            account = this.accountDaoImpl.getAccount(0);
        } catch (Exception e) {
            fail("Account was not retrieved correctly with error : " + e.getLocalizedMessage());
        }
        assertNotNull(account);
        assertEquals(account.getName(), this.testAccountNotActivated.getName());
        assertEquals(account.getId().intValue(), this.testAccountNotActivated.getId().intValue());
        assertEquals(account.getPassword(), this.testAccountNotActivated.getPassword());
        assertFalse(account.getActivated());
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_String_InvalidName() {
        Account account = null;
        // Check for invalid input
        try {
            account = null;
            account = this.accountDaoImpl.getAccount("kajhdfkahdlkh");
            fail("Failed to throw DataAccessException for invalid input");
        } catch (DataAccessException e) {
            assertNull(account);
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_String_BlankName() {
        Account account = null;
        // Check for empty string input
        try {
            account = null;
            account = this.accountDaoImpl.getAccount("");
            fail("Failed to throw DataAccessException for blank");
        } catch (DataAccessException e) {
            assertNull(account);
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_String_NullName() {
        Account account = null;
        // Check for null input
        try {
            account = null;
            account = this.accountDaoImpl.getAccount((String) null);
            fail("Failed to throw DataAccessException for null");
        } catch (DataAccessException e) {
            assertNull(account);
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testGetAccount_String_ValidName() {
        Account account = null;
        // Check if account was retieved correctly
        try {
            account = this.accountDaoImpl.getAccount("Hello");
        } catch (Exception e) {
            fail("Account was not retrieved correctly with error : " + e.getLocalizedMessage());
        }
        assertNotNull(account);
        assertEquals(account.getName(), this.testAccountNotActivated.getName());
        assertEquals(account.getId().intValue(), this.testAccountNotActivated.getId().intValue());
        assertEquals(account.getPassword(), this.testAccountNotActivated.getPassword());
        assertFalse(account.getActivated());
    }

    /**
     * Test of saveAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testSaveAccount_NullAccount() {
        // Save null account
        try {
            this.accountDaoImpl.saveAccount(null);
            fail("Failed to throw DataAccessException for null");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testSaveAccount_NewAccount() {
        // Save new Account
        try {
            Account account = new Account(3, "World");
            this.accountDaoImpl.saveAccount(account);
            Account returnAccount = this.accountDaoImpl.getAccount("World");
            assertNotNull(returnAccount);
            assertEquals(account.getId().intValue(), returnAccount.getId().intValue());
            assertEquals(account.getName(), returnAccount.getName());
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown for new Account");
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testSaveAccount_OldAccount() {
        // Save old Account
        try {
            this.accountDaoImpl.saveAccount(testAccountNotActivated);
        } catch (Exception e) {
            fail("Could not save test account for saveAccount()");
        }
        try {
            Account account = this.accountDaoImpl.getAccount(0);
            account.setPassword("1234");
            this.accountDaoImpl.saveAccount(account);
            account = this.accountDaoImpl.getAccount(0);
            assertNotNull(account);
            assertEquals(account.getName(), this.testAccountNotActivated.getName());
            assertEquals(account.getId().intValue(), this.testAccountNotActivated.getId().intValue());
            assertEquals(account.getPassword(), this.testAccountNotActivated.getPassword());
            assertFalse(account.getActivated());
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown for resave");
        }
    }

    /**
     * Test of removeAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testRemoveAccount_NegId() {
        // Check for negative input
        try {
            this.accountDaoImpl.removeAccount(-1);
            fail("Failed to throw DataAccessException");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testRemoveAccount_InvalidId() {
        // Check for accounts not in db
        try {
            this.accountDaoImpl.removeAccount(9999);
            fail("Failed to throw DataAccessException");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testRemoveAccount_NullId() {
        // Check for null input
        try {
            this.accountDaoImpl.removeAccount((Integer) null);
            fail("Failed to throw DataAccessException");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testRemoveAccount_ValidId() {
        try {
            this.accountDaoImpl.saveAccount(testAccountNotActivated);
        } catch (Exception e) {
            fail("Could not save test account for removeAccount()");
        }
        // Check if remove is successful
        try {


            this.accountDaoImpl.removeAccount(0);
            List<Account> accounts = this.accountDaoImpl.getAccounts();
            assertEquals(accounts.size(), 1);
            assertEquals(accounts.get(0).getName(), this.testAccountActivated.getName());
            assertEquals(accounts.get(0).getId().intValue(), this.testAccountActivated.getId().intValue());
            assertEquals(accounts.get(0).getPassword(), this.testAccountActivated.getPassword());
            assertTrue(accounts.get(0).getActivated());
        } catch (DataAccessException e) {
            fail("DataAccessException was thrown");
        }
    }
}
