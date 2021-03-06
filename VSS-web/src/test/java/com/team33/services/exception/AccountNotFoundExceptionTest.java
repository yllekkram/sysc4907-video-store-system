/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.exception;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mark
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/dao/dao-test.xml"})
@Transactional
public class AccountNotFoundExceptionTest {
    
    public AccountNotFoundExceptionTest() {
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

    @Test
    public void testConstructor_String() {
        String string = "foo";
        AccountNotFoundException e = new AccountNotFoundException(string);
        
        assertEquals(e.getMessage(), string);
    }
    
    @Test
    public void testConstructor_StringThrowable() {
        String string = "foo";
        Throwable throwable = new Throwable("bar");
        AccountNotFoundException e = new AccountNotFoundException(string, throwable);
        
        assertEquals(e.getMessage(), string);
        assertEquals(e.getCause(), throwable);
    }
}