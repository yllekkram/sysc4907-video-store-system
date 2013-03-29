/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Account;
import com.team33.entities.Orders;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.AccountNotFoundException;
import com.team33.services.exception.AuthenticationException;
import com.team33.services.exception.DataAccessException;
import com.team33.services.exception.LoginException;
import com.team33.services.exception.RegistrationException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Daywalker
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/service/service-test.xml"})
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
        this.accountServiceImpl.setAccountDao(new AccountDaoImplTestStub());
    }

    @After
    public void tearDown() {
    }

    @Test
    @Rollback(true)
    public void testLoginAccount_ValidUsernameActivated() {
        int account = -9999;
        try {
            account = this.accountServiceImpl.loginAccount("Hello", "World");
        } catch (AuthenticationException ex) {
            fail("AuthenticationException should not be thrown");
        } catch (AccountNotFoundException ex) {
            fail("AccountNotFoundException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (LoginException ex) {
            fail("LoginException should not be thrown");
        }
        assertNotNull(account);
        assertEquals(account, 1);
    }

    @Test
    @Rollback(true)
    public void testLoginAccount_NullUsername() {
        int account = -9999;
        try {
            account = this.accountServiceImpl.loginAccount(null, "World");
        } catch (AuthenticationException ex) {
            fail("AuthenticationException should not be thrown");
        } catch (AccountNotFoundException ex) {
            fail("AccountNotFoundException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (LoginException ex) {
        }
    }

    @Test
    @Rollback(true)
    public void testLoginAccount_BlankUsername() {
        int account = -9999;
        try {
            account = this.accountServiceImpl.loginAccount("", "World");
        } catch (AuthenticationException ex) {
            fail("AuthenticationException should not be thrown");
        } catch (AccountNotFoundException ex) {
            fail("AccountNotFoundException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (LoginException ex) {
        }
    }

    @Test
    @Rollback(true)
    public void testLoginAccount_InvalidUsername() {
        int account = -9999;
        try {
            account = this.accountServiceImpl.loginAccount("People", "World");
        } catch (AuthenticationException ex) {
            fail("AuthenticationException should not be thrown");
        } catch (AccountNotFoundException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (LoginException ex) {
            fail("LoginException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testLoginAccount_NullPassword() {
        int account = -9999;
        try {
            account = this.accountServiceImpl.loginAccount("Hello", null);
        } catch (AuthenticationException ex) {
            fail("AuthenticationException should not be thrown");
        } catch (AccountNotFoundException ex) {
            fail("AccountNotFoundException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (LoginException ex) {
        }
    }

    @Test
    @Rollback(true)
    public void testLoginAccount_BlankPassword() {
        int account = -9999;
        try {
            account = this.accountServiceImpl.loginAccount("Hello", "");
        } catch (AuthenticationException ex) {
            fail("AuthenticationException should not be thrown");
        } catch (AccountNotFoundException ex) {
            fail("AccountNotFoundException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (LoginException ex) {
        }
    }

    @Test
    @Rollback(true)
    public void testLoginAccount_InvalidPassword() {
        int account = -9999;
        try {
            account = this.accountServiceImpl.loginAccount("Hello", "People");
        } catch (AuthenticationException ex) {
        } catch (AccountNotFoundException ex) {
            fail("AccountNotFoundException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (LoginException ex) {
            fail("LoginException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRegisterAccount_ValidUsernameNotInDB() {
        try {
            this.accountServiceImpl.registerAccount("Hello", "World");
        } catch (RegistrationException ex) {
            fail("RegistrationException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRegisterAccount_NullUsername() {
        try {
            this.accountServiceImpl.registerAccount(null, "World");
        } catch (RegistrationException ex) {
            assertEquals(ex.getLocalizedMessage(), "Registration Failed");
        }
        fail("RegistrationException was not thrown");
    }

    @Test
    @Rollback(true)
    public void testRegisterAccount_BlankUsername() {
        try {
            this.accountServiceImpl.registerAccount("", "World");
        } catch (RegistrationException ex) {
            assertEquals(ex.getLocalizedMessage(), "Registration Failed");
        }
        fail("RegistrationException was not thrown");
    }

    @Test
    @Rollback(true)
    public void testRegisterAccount_InvalidUsername() {
        try {
            this.accountServiceImpl.registerAccount("People", "World");
        } catch (RegistrationException ex) {
            fail("RegistrationException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRegisterAccount_NullPassword() {
        try {
            this.accountServiceImpl.registerAccount("Hello", null);
        } catch (RegistrationException ex) {
            assertEquals(ex.getLocalizedMessage(), "Registration Failed");
        }
        fail("RegistrationException was not thrown");
    }

    @Test
    @Rollback(true)
    public void testRegisterAccount_BlankPassword() {
        try {
            this.accountServiceImpl.registerAccount("Hello", "");
        } catch (RegistrationException ex) {
            assertEquals(ex.getLocalizedMessage(), "Registration Failed");
        }
        fail("RegistrationException was not thrown");
    }

    @Test
    @Rollback(true)
    public void testRegisterAccount_InavlidUsername() {
        try {
            this.accountServiceImpl.registerAccount("Hello", "People");
        } catch (RegistrationException ex) {
            fail("RegistrationException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testGetAccount_NullId() {
        Account account = null;
        try {
            account = this.accountServiceImpl.getAccount(null);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testGetAccount_NegId() {
        Account account = null;
        try {
            account = this.accountServiceImpl.getAccount(-1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testGetAccount_InvalidId() {
        Account account = null;
        try {
            account = this.accountServiceImpl.getAccount(9999);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testGetAccount_ValidId() {
        Account account = null;
        try {
            account = this.accountServiceImpl.getAccount(0);
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        }
        assertNotNull(account);
        assertEquals(account.getId().intValue(), 0);
    }

    @Test
    @Rollback(true)
    public void testGetAccounts() {
        List<Account> accounts = null;
        try {
            accounts = this.accountServiceImpl.getAccounts();
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        }
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    @Rollback(true)
    public void testRemoveAccount_NullId() {
        try {
            this.accountServiceImpl.removeAccount(null);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveAccount_NegId() {
        try {
            this.accountServiceImpl.removeAccount(-1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveAccount_InvalidId() {
        try {
            this.accountServiceImpl.removeAccount(9999);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveAccount_ValidId() {
        try {
            this.accountServiceImpl.removeAccount(0);
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddOrder_ValidId() {
        try {
            this.accountServiceImpl.addOrder(0, new Orders(0, 0));
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddOrder_NullId() {
        try {
            this.accountServiceImpl.addOrder(null, new Orders(0, 0));
            fail("DataAccessExceptiion should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddOrder_NegId() {
        try {
            this.accountServiceImpl.addOrder(-1, new Orders(0, 0));
            fail("DataAccessExceptiion should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddOrder_InvalidId() {
        try {
            this.accountServiceImpl.addOrder(9999, new Orders(0, 0));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddOrder_NullOrder() {
        try {
            this.accountServiceImpl.addOrder(0, null);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddOrder_InvalidOrder() {
        try {
            this.accountServiceImpl.addOrder(0, new Orders(9999, 0));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_ValidId() {
        try{
            this.accountServiceImpl.removeOrder(0, new Orders(0, 0));
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_NullId() {
        try{
            this.accountServiceImpl.removeOrder(null, new Orders(0, 0));
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_NegId() {
        try{
            this.accountServiceImpl.removeOrder(-1, new Orders(0, 0));
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_InvalidId() {
        try{
            this.accountServiceImpl.removeOrder(9999, new Orders(0, 0));
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_NullOrder() {
        try{
            this.accountServiceImpl.removeOrder(0, null);
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_InvalidOrder() {
        try{
            this.accountServiceImpl.removeOrder(0, new Orders(0, 9999));
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }
}
