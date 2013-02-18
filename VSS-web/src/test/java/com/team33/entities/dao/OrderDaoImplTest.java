/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.Account;
import com.team33.entities.LoginToken;
import com.team33.entities.Orders;
import com.team33.entities.Purchase;
import com.team33.entities.Rental;
import com.team33.services.exception.DataAccessException;
import java.util.Date;
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
public class OrderDaoImplTest {

    @Autowired
    private OrdersDao orderDao;

    public OrderDaoImplTest() {
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

    public void setOrderDao(OrdersDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Test of getOrders method, of class Order1DaoImpl.
     */
    @Test
    @Rollback(true)
    public void testGetOrders_NullToken() {
        try {
            List<Orders> order = this.orderDao.getOrders(null);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testGetOrders_NullToken() passed");
    }

    @Test
    @Rollback(true)
    public void testGetOrders_InvalidToken() {
        LoginToken testToken = new LoginToken();
        try {
            List<Orders> order = this.orderDao.getOrders(testToken);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testGetOrders_InvalidToken() passed");
    }

    @Test
    @Rollback(true)
    public void testGetOrders_ValidToken() {
        LoginToken testToken = new LoginToken();
        testToken.setAccount(new Account(0, "Hello World"));
        List<Orders> order = this.orderDao.getOrders(testToken);
        assertNotNull(order);
        assertTrue(order.size() > 0);
        System.out.println("testGetOrders_ValidToken() passed");
    }

    /**
     * Test of getOrder method, of class Order1DaoImpl.
     */
    @Test
    @Rollback(true)
    public void testGetOrder_NegId() {
        try {
            Orders order = this.orderDao.getOrder(-1);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testGetOrder_NegId() passed");
    }

    @Test
    @Rollback(true)
    public void testGetOrder_InvalidId() {
        try {
            Orders order = this.orderDao.getOrder(9999);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testGetOrder_InvalidId() passed");
    }

    @Test
    @Rollback(true)
    public void testGetOrder_ValidId() {
        Orders order = this.orderDao.getOrder(0);
        assertNotNull(order);
        // TODO finish checking order on finer details
        System.out.println("testGetOrder_ValidId() passed");
    }

    /**
     * Test of saveOrder method, of class Order1DaoImpl.
     */
    @Test
    @Rollback(true)
    public void testSaveOrder_NullOrder() {
        try {
            this.orderDao.saveOrder(null);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSaveOrder_NullOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testSaveOrder_InvalidOrder() {
        try {
            this.orderDao.saveOrder(new Orders(-1, -1));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSaveOrder_InvalidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testSaveOrder_ValidOrder() {
        try {
            this.orderDao.saveOrder(new Orders(0, 0));
        } catch (DataAccessException e) {
            fail("Error should not be thrown");
        }
        System.out.println("testSaveOrder_ValidOrder() passed");
    }

    /**
     * Test of removeOrder method, of class Order1DaoImpl.
     */
    @Test
    @Rollback(true)
    public void testRemoveOrder_Integer_NegOrder() {
        try {
            this.orderDao.removeOrder(-1);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveOrder_Integer_NegOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_Integer_InvalidOrder() {
        try {
            this.orderDao.removeOrder(9999);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveOrder_Integer_InvalidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_Integer_ValidOrder() {
        try {
            this.orderDao.removeOrder(0);
        } catch (DataAccessException e) {
            fail("Error should not be thrown");
        }
        System.out.println("testRemoveOrder_Integer_ValidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_NullOrder() {
        try {
            this.orderDao.removePurchase(null, new Purchase(0, 0, 0, 0));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemovePurchase_NullOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_InvalidOrder() {
        try {
            this.orderDao.removePurchase(new Orders(-1, -1), new Purchase(0, 0, 0, 0));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemovePurchase_InvalidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_ValidOrder() {
        try {
            this.orderDao.removePurchase(new Orders(0, 0), new Purchase(0, 0, 0, 0));
        } catch (DataAccessException e) {
            fail("Error was thrown : " + e.getLocalizedMessage());
        }
        System.out.println("testRemovePurchase_ValidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_NullPurchase() {
        try {
            this.orderDao.removePurchase(new Orders(0, 0), null);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemovePurchase_NullPurchase() passed");
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_InvalidPurchase() {
        try {
            this.orderDao.removePurchase(new Orders(0, 0), new Purchase(-1, -1, -1, -1));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemovePurchase_InvalidPurchase() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NullOrder() {
        try {
            this.orderDao.removeRental(null, new Rental(0, 0, 0, 0, new Date(99999)));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveRental_NullOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_InvalidOrder() {
        try {
            this.orderDao.removeRental(new Orders(-1, -1), new Rental(0, 0, 0, 0, new Date(99999)));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveRental_InvalidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_ValidOrder() {
        try {
            this.orderDao.removeRental(new Orders(0, 0), new Rental(0, 0, 0, 0, new Date(99999)));
        } catch (DataAccessException e) {
            fail("Error was thrown : " + e.getLocalizedMessage());
        }
        System.out.println("testRemoveRental_ValidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NullRental() {
        try {
            this.orderDao.removeRental(new Orders(0, 0), null);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveRental_NullRental() passed");
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_InvalidRental() {
        try {
            this.orderDao.removeRental(new Orders(0, 0), new Rental(-1, -1, -1, -1, new Date(99999)));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testRemoveRental_InvalidRental() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSavePurchase_NullOrder() {
        try {
            this.orderDao.savePurchase(null, new Purchase(0, 0, 0, 0));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSavePurchase_NullOrder() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSavePurchase_InvalidOrder() {
        try {
            this.orderDao.savePurchase(new Orders(-1, -1), new Purchase(0, 0, 0, 0));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSavePurchase_InvalidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testSavePurchase_ValidOrder() {
        try {
            this.orderDao.savePurchase(new Orders(0, 0), new Purchase(0, 0, 0, 0));
        } catch (DataAccessException e) {
            fail("Error was thrown : " + e.getLocalizedMessage());
        }
        System.out.println("testSavePurchase_ValidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testSavePurchase_NullPurchase() {
        try {
            this.orderDao.savePurchase(new Orders(0, 0), null);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSavePurchase_NullPurchase() passed");
    }

    @Test
    @Rollback(true)
    public void testSavePurchase_InvalidPurchase() {
        try {
            this.orderDao.savePurchase(new Orders(0, 0), new Purchase(-1, -1, -1, -1));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSavePurchase_InvalidPurchase() passed");
    }

    @Test
    @Rollback(true)
    public void testSaveRental_NullOrder() {
        try {
            this.orderDao.saveRental(null, new Rental(0, 0, 0, 0, new Date(99999)));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSaveRental_NullOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testSaveRental_InvalidOrder() {
        try {
            this.orderDao.saveRental(new Orders(-1, -1), new Rental(0, 0, 0, 0, new Date(99999)));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSaveRental_InvalidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testSaveRental_ValidOrder() {
        try {
            this.orderDao.saveRental(new Orders(0, 0), new Rental(0, 0, 0, 0, new Date(99999)));
        } catch (DataAccessException e) {
            fail("Error was thrown : " + e.getLocalizedMessage());
        }
        System.out.println("testSaveRental_ValidOrder() passed");
    }

    @Test
    @Rollback(true)
    public void testSaveRental_NullRental() {
        try {
            this.orderDao.saveRental(new Orders(0, 0), null);
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSaveRental_NullRental() passed");
    }

    @Test
    @Rollback(true)
    public void testSaveRental_InvalidRental() {
        try {
            this.orderDao.saveRental(new Orders(0, 0), new Rental(-1, -1, -1, -1, new Date(99999)));
            fail("Error was not thrown");
        } catch (DataAccessException e) {
        }
        System.out.println("testSaveRental_InvalidRental() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetLoginToken_NegUUID(){
        try{
            this.orderDao.getLoginToken(-1);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        System.out.println("testGetLoginToken_NegUUID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetLoginToken_InvalidUUID(){
        try{
            this.orderDao.getLoginToken(9999);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        System.out.println("testGetLoginToken_InvalidUUID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetLoginToken_ValidUUID(){
        LoginToken testToken = this.orderDao.getLoginToken(0);
        assertNotNull(testToken);
        assertEquals(testToken.getLogintokenPK().getId(), 0);
        System.out.println("testGetLoginToken_ValidUUID() passed");
    }
}
