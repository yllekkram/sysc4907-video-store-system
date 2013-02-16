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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
public class OrderDaoImplTest {
    
    private OrdersDao order1Dao;
    
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
    
    public void setOrder1Dao(OrdersDao order1Dao){
        this.order1Dao = order1Dao;
    }

    /**
     * Test of getOrders method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    public void testGetOrders() {
        System.out.println("getOrders");
        try{
            List<Orders> order = this.order1Dao.getOrders(null);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        LoginToken testToken = new LoginToken();
        Account testAccount = new Account(-1);
        testAccount.setActivated(true);
        testToken.setAccount(testAccount);
        
        try{
            List<Orders> order = this.order1Dao.getOrders(testToken);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        
        testToken = new LoginToken();
        testAccount = new Account(0);
        testAccount.setActivated(true);
        testToken.setAccount(testAccount);
        
        try{
            List<Orders> order = this.order1Dao.getOrders(testToken);
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
    }

    /**
     * Test of getOrder method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    public void testGetOrder() {
        System.out.println("getOrder(Integer) -> [DataAccessException]");
        try{
            Orders order = this.order1Dao.getOrder(-1);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        
        try{
            Orders order = this.order1Dao.getOrder(9999);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        
        try{
            Orders order = this.order1Dao.getOrder(0);
            assertNotNull(order);
            // TODO finish checking order on finer details
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
    }

    /**
     * Test of saveOrder method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveOrder() {
        System.out.println("saveOrder(Order1) -> [DataAccessException]");
        try{
            this.order1Dao.saveOrder(null);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        
        try{
            this.order1Dao.saveOrder(new Orders(-1, -1));
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        
        try{
            this.order1Dao.saveOrder(new Orders(0, 0));
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
    }

    /**
     * Test of removeOrder method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testRemoveOrder() {
        System.out.println("removeOrder(Integer) -> [DataAccessException]");
        try{
            this.order1Dao.removeOrder(-1);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        
        try{
            this.order1Dao.removeOrder(9999);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        
        try{
            this.order1Dao.removeOrder(0);
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
    }

    /**
     * Test of createInvoice method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateInvoice() {
        System.out.println("createInvoice(Order1) -> [DataAccessException]");
//        try{
//            this.order1Dao.createInvoice(null);
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.createInvoice(new Orders(-1, -1));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.createInvoice(new Orders(0, 0));
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
    }

    /**
     * Test of removePurchase method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testRemovePurchase() {
        System.out.println("removePurchase(Order, Purchase) -> [DataAccessException]");
        try{
            this.order1Dao.removePurchase(null, null);
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
        
        try{
            this.order1Dao.removePurchase(new Orders(), null);
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
        
        try{
            this.order1Dao.removePurchase(null, new Purchase());
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
//        
//        try{
//            this.order1Dao.removePurchase(new Orders(-1, -1), new Purchase(0, 0, 0));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.removePurchase(new Orders(0, 0), new Purchase(-1, -1, -1));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.removePurchase(new Orders(0, 0), new Purchase(0, 0, 0));
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
    }

    /**
     * Test of removeRental method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testRemoveRental() {
        System.out.println("removeRental(Order, Rental) -> [DataAccessException]");
        try{
            this.order1Dao.removeRental(null, null);
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
        
        try{
            this.order1Dao.removeRental(new Orders(), null);
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
        
        try{
            this.order1Dao.removeRental(null, new Rental());
        }catch(DataAccessException e){
            fail("Error should not be thrown");
        }
//        
//        try{
//            this.order1Dao.removeRental(new Orders(-1, -1), new Rental(0, 0, 0));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.removeRental(new Orders(0, 0), new Rental(-1, -1, -1));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.removeRental(new Orders(0, 0), new Rental(0, 0, 0));
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
    }

    /**
     * Test of savePurchase method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testSavePurchase() {
        System.out.println("savePurchase(Order, Purchase) -> [DataAccessException]");
//        try{
//            this.order1Dao.savePurchase(null, null);
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
//        
//        try{
//            this.order1Dao.savePurchase(new Order1(), null);
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
//        
//        try{
//            this.order1Dao.savePurchase(null, new Purchase());
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
//        
//        try{
//            this.order1Dao.savePurchase(new Order1(-1, -1), new Purchase(0, 0, 0));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.savePurchase(new Order1(0, 0), new Purchase(-1, -1, -1));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.savePurchase(new Order1(0, 0), new Purchase(0, 0, 0));
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
    }

    /**
     * Test of saveRental method, of class Order1DaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveRental() {
        System.out.println("saveRental(Order1, Rental) -> [DataAccessException]");
//        try{
//            this.order1Dao.saveRental(null, null);
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
//        
//        try{
//            this.order1Dao.saveRental(new Order1(), null);
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
//        
//        try{
//            this.order1Dao.saveRental(null, new Rental());
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
//        
//        try{
//            this.order1Dao.saveRental(new Order1(-1, -1), new Rental(0, 0, 0));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.saveRental(new Order1(0, 0), new Rental(-1, -1, -1));
//            fail("Error was not thrown");
//        }catch(DataAccessException e){
//        }
//        
//        try{
//            this.order1Dao.saveRental(new Order1(0, 0), new Rental(0, 0, 0));
//        }catch(DataAccessException e){
//            fail("Error should not be thrown");
//        }
    }
}
