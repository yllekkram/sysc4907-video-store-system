/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.Orders;
import com.team33.entities.Purchase;
import com.team33.entities.Rental;
import com.team33.entities.dao.OrdersDaoImpl;
import com.team33.services.exception.DataAccessException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caleb
 */
public class OrderDaoImplTestStub extends OrdersDaoImpl{
    
    private ArrayList<Orders> dummyOrderList;
    
    public OrderDaoImplTestStub(){
        this.dummyOrderList = new ArrayList<Orders>();
    }
    
    @Override
    public List<Orders> getOrders(LoginToken loginToken) throws DataAccessException {
        if (loginToken == null){
            throw new DataAccessException("Dummy Message");
        }else if (loginToken.getLogintokenPK() == null){
            throw new DataAccessException("Dummy Message");
        }else if (loginToken.getAccount().getId().intValue() == 0){
            return this.dummyOrderList;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public Orders getOrder(Integer orderId, Integer accountID) throws DataAccessException {
        if (orderId == null){
            throw new DataAccessException("Dummy Message");
        }else if(orderId.intValue() == -1){
            throw new DataAccessException("Dummy Message");
        }else if(orderId.intValue() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(orderId.intValue() == 0){
        
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void saveOrder(Orders order) throws DataAccessException {
        if (order == null){
            throw new DataAccessException("Dummy Message");
        }else if (order.getOrdersPK().getId() == -1){
            throw new DataAccessException("Dummy Message");
        }else if (order.getOrdersPK().getId() == 0){
            return;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void removeOrder(Integer orderId) {
        if (orderId == null){
            throw new DataAccessException("Dummy Message");
        }else if(orderId.intValue() == -1){
            throw new DataAccessException("Dummy Message");
        }else if(orderId.intValue() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(orderId.intValue() == 0){
            return;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void removePurchase(Orders order, Purchase purchase) throws DataAccessException {
        if (order == null){
            throw new DataAccessException("Dummy Message");
        }else if (purchase == null){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == -1 || order.getOrdersPK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(purchase.getPurchasePK().getId() == -1 || purchase.getPurchasePK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == 0 && purchase.getPurchasePK().getId() == 0){
            return;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void removeRental(Orders order, Rental rental) throws DataAccessException {
        if (order == null){
            throw new DataAccessException("Dummy Message");
        }else if (rental == null){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == -1 || order.getOrdersPK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(rental.getRentalPK().getId() == -1 || rental.getRentalPK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == 0 && rental.getRentalPK().getId() == 0){
            return;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void savePurchase(Orders order, Purchase purchase) throws DataAccessException {
        if (order == null){
            throw new DataAccessException("Dummy Message");
        }else if (purchase == null){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == -1 || order.getOrdersPK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(purchase.getPurchasePK().getId() == -1 || purchase.getPurchasePK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == 0 && purchase.getPurchasePK().getId() == 0){
            return;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public void saveRental(Orders order, Rental rental) throws DataAccessException {
        if (order == null){
            throw new DataAccessException("Dummy Message");
        }else if (rental == null){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == -1 || order.getOrdersPK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(rental.getRentalPK().getId() == -1 || rental.getRentalPK().getId() == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(order.getOrdersPK().getId() == 0 && rental.getRentalPK().getId() == 0){
            return;
        }
        throw new DataAccessException("Should not reach here");    
    }
    
    @Override
    public LoginToken getLoginToken(int uuid) throws DataAccessException {
        if (uuid == -1){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 0){
            return new LoginToken(0, 0);
        }
        throw new DataAccessException("Should not reach here");    
    }
}
