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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/dao/dao-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
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

    public void setAccountDao(AccountDaoImpl accountDao) {
        this.accountDaoImpl = accountDao;
    }

    /**
     * Test of getAccounts method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    public void testGetAccounts_NotNull() {
        assertNotNull(this.accountDaoImpl.getAccounts());
        System.out.println("testGetAccounts_NotNull() passed");
    }

    @Test
    @Rollback(true)
    public void testGetAccounts_NotNegSize() {
        assertTrue(this.accountDaoImpl.getAccounts().size() >= 0);
        System.out.println("testGetAccounts_NotNegSize() passed");
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    public void testGetAccount_Integer_NegId() {
        Account account = null;
        // Check for negative input
        try {
            account = this.accountDaoImpl.getAccount(-1);
            fail("Failed to throw DataAccessException for -1");
        } catch (DataAccessException e) {
            assertNull(account);
        }
        System.out.println("testGetAccount_Integer_NegId() passed");
    }

    @Test
    @Rollback(true)
    public void testGetAccount_Integer_InvalidId() {
        Account account = null;
        try {
            account = null;
            account = this.accountDaoImpl.getAccount(9999);
            fail("Failed to throw DataAccessException for 9999");
        } catch (DataAccessException e) {
            assertNull(account);
        }
        System.out.println("testGetAccount_Integer_InvalidId() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testGetAccount_Integer_NullId() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testGetAccount_Integer_ValidId() passed");
    }

    /**
     * Test of getAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
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
        System.out.println("testGetAccount_String_InvalidName() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testGetAccount_String_BlankName() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testGetAccount_String_NullName() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testGetAccount_String_ValidName() passed");
    }

    /**
     * Test of saveAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    public void testSaveAccount_NullAccount() {
        // Save null account
        try {
            this.accountDaoImpl.saveAccount(null);
            fail("Failed to throw DataAccessException for null");
        } catch (DataAccessException e) {
        }
        System.out.println("testSaveAccount_NullAccount() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveAccount_ValidAccount(){
        try{
            this.accountDaoImpl.saveAccount(new Account(1, "Test1"));
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }
        System.out.println("testSaveAccount_ValidAccount() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveAccount_NullID(){
        try{
            this.accountDaoImpl.saveAccount(new Account(null, "Test1"));
            fail("Failed to throw DataAccessException for null");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveAccount_NullID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveAccount_NegID(){
        try{
            this.accountDaoImpl.saveAccount(new Account(-1, "Test1"));
            fail("Failed to throw DataAccessException for null");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveAccount_NegID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveAccount_InvalidID(){
        try{
            this.accountDaoImpl.saveAccount(new Account(9999, "Test1"));
            fail("Failed to throw DataAccessException for null");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveAccount_InvalidID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveAccount_NullName(){
        try{
            this.accountDaoImpl.saveAccount(new Account(1, null));
            fail("Failed to throw DataAccessException for null");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveAccount_NullName() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveAccount_BlankName(){
        try{
            this.accountDaoImpl.saveAccount(new Account(1, ""));
            fail("Failed to throw DataAccessException for null");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveAccount_BlankName() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveAccount_InvalidName(){
        try{
            this.accountDaoImpl.saveAccount(new Account(1, "Invalid"));
            fail("Failed to throw DataAccessException for null");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveAccount_InvalidName() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testSaveAccount_NewAccount() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testSaveAccount_OldAccount() passed");
    }

    /**
     * Test of removeAccount method, of class AccountDaoImpl.
     */
    @Test
    @Rollback(true)
    public void testRemoveAccount_NegId() {
        // Check for negative input
        try {
            this.accountDaoImpl.removeAccount(-1);
            fail("Failed to throw DataAccessException");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveAccount_NegId() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveAccount_InvalidId() {
        // Check for accounts not in db
        try {
            this.accountDaoImpl.removeAccount(9999);
            fail("Failed to throw DataAccessException");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveAccount_InvalidId() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveAccount_NullId() {
        // Check for null input
        try {
            this.accountDaoImpl.removeAccount((Integer) null);
            fail("Failed to throw DataAccessException");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveAccount_NullId() passed");
    }

    @Test
    @Rollback(true)
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
        System.out.println("testRemoveAccount_ValidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testRemoveLoginToken_NullId(){
        try{
            this.accountDaoImpl.removeLoginToken(null);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        
        }
        System.out.println("testRemoveLoginToken_NullId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testRemoveLoginToken_NegId(){
        try{
            this.accountDaoImpl.removeLoginToken(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        
        }
        System.out.println("testRemoveLoginToken_NegId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testRemoveLoginToken_InvalidId(){
        try{
            this.accountDaoImpl.removeLoginToken(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        
        }
        System.out.println("testRemoveLoginToken_InvalidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testRemoveLoginToken_ValidId(){
        try{
            this.accountDaoImpl.removeLoginToken(1);
        }catch(DataAccessException e){
            fail("DataAccessException was thrown");
        }
        System.out.println("testRemoveLoginToken_ValidId() passed");
    }
    
}
