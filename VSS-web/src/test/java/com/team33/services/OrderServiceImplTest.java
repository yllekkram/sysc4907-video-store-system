/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/service/service-test.xml"})
public class OrderServiceImplTest {
    
    private OrderServiceImpl orderServiceImpl;
    
    public OrderServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        orderServiceImpl = new OrderServiceImpl();
        orderServiceImpl.setOrdersDao(new OrderDaoImplTestStub());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isActivated method, of class OrderServiceImpl.
     */
    @Test
    public void testIsActivated_ValidId() {
        try{
            assertTrue(this.orderServiceImpl.isActivated(0));
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testIsActivated_NegId(){
        try{
            assertTrue(this.orderServiceImpl.isActivated(-1));
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testIsActivated_InvalidId(){
        try{
            assertTrue(this.orderServiceImpl.isActivated(9999));
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of addPurchase method, of class OrderServiceImpl.
     */
    @Test
    public void testAddPurchase() throws Exception {

    }

    /**
     * Test of addRental method, of class OrderServiceImpl.
     */
    @Test
    public void testAddRental() throws Exception {

    }

    /**
     * Test of confirmPayment method, of class OrderServiceImpl.
     */
    @Test
    public void testConfirmPayment() throws Exception {

    }

    /**
     * Test of getOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {

    }

    /**
     * Test of getOrders method, of class OrderServiceImpl.
     */
    @Test
    public void testGetOrders() throws Exception {

    }

    /**
     * Test of removeOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {

    }

    /**
     * Test of removePurchase method, of class OrderServiceImpl.
     */
    @Test
    public void testRemovePurchase_ValidVideoId() {
        try{
            this.orderServiceImpl.removePurchase(0, 0, 0);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_NullVideoId() {
        try{
            this.orderServiceImpl.removePurchase(null, 0, 0);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_NegVideoId() {
        try{
            this.orderServiceImpl.removePurchase(-1, 0, 0);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_InvalidVideoId() {
        try{
            this.orderServiceImpl.removePurchase(9999, 0, 0);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_NullOrderId() {
        try{
            this.orderServiceImpl.removePurchase(0, null, 0);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_NegOrderId() {
        try{
            this.orderServiceImpl.removePurchase(0, -1, 0);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_InvalidOrderId(){
        try{
            this.orderServiceImpl.removePurchase(0, 9999, 0);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_NegAccountId(){
        try{
            this.orderServiceImpl.removePurchase(0, 0, -1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testRemovePurchase_InvalidAccountId(){
        try{
            this.orderServiceImpl.removePurchase(0, 0, 9999);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of removeRental method, of class OrderServiceImpl.
     */
    @Test
    public void testRemoveRental_ValidVideoId() {
        try{
            this.orderServiceImpl.removeRental(0, 0, 0, new Date(0L));
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_NullVideoId() {
        try{
            this.orderServiceImpl.removeRental(null, 0, 0, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_NegVideoId() {
        try{
            this.orderServiceImpl.removeRental(-1, 0, 0, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_InvalidVideoId() {
        try{
            this.orderServiceImpl.removeRental(9999, 0, 0, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_NullOrderId() {
        try{
            this.orderServiceImpl.removeRental(0, null, 0, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_NegOrderId() {
        try{
            this.orderServiceImpl.removeRental(0, -1, 0, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_InvalidOrderId(){
        try{
            this.orderServiceImpl.removeRental(0, 9999, 0, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_NegAccountId(){
        try{
            this.orderServiceImpl.removeRental(0, 0, -1, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_InvalidAccountId() {
        try{
            this.orderServiceImpl.removeRental(0, 0, 9999, new Date(0L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_NullDate() {
        try{
            this.orderServiceImpl.removeRental(0, 0, 0, null);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testRemoveRental_InvalidDate() {
        try{
            this.orderServiceImpl.removeRental(0, 0, 0, new Date(1000L));
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
}
