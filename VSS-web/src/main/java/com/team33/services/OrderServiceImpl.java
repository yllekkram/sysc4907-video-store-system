/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.Order1;
import com.team33.entities.dao.Order1DaoImpl;
import com.team33.services.exception.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samual
 */
public class OrderServiceImpl implements OrderService {
    
    //tells Spring to inject the dependency
    @Autowired
    private Order1DaoImpl order1DaoImpl;

    public void setOrder1DaoImpl(Order1DaoImpl dao) {
        this.order1DaoImpl = dao;
    }

    public Order1DaoImpl getOrder1DaoImpl() {
        return this.order1DaoImpl;
    }

    @Override
    public void addOrder(Order1 order,LoginToken loginToken) throws DataAccessException,AccountNotActivatedException {
         if(!loginToken.getAccount().getActivated()){
            throw new AccountNotActivatedException("Account Inactive");
        }
    }

    @Override
    public void confirmPayment(Integer orderId,LoginToken loginToken) throws AccountNotActivatedException{
         if(!loginToken.getAccount().getActivated()){
            throw new AccountNotActivatedException("Account Inactive");
        }
    }

    @Override
    public Order1 getOrder(Integer orderId,LoginToken loginToken) throws DataAccessException,AccountNotActivatedException{
         if(!loginToken.getAccount().getActivated()){
            throw new AccountNotActivatedException("Account Inactive");
        }
        return this.getOrder(orderId,loginToken);
    }

    @Override
    public List<Order1> getOrders(LoginToken loginToken) throws DataAccessException,AccountNotActivatedException{
         if(!loginToken.getAccount().getActivated()){
            throw new AccountNotActivatedException("Account Inactive");
        }
        return this.getOrder1DaoImpl().getOrders(loginToken);
    }
    

    @Override
    public void removeOrder(Integer orderID,LoginToken loginToken) throws AccountNotActivatedException{
         if(!loginToken.getAccount().getActivated()){
            throw new AccountNotActivatedException("Account Inactive");
        }
        this.order1DaoImpl.removeOrder(orderID,loginToken);
    }
    
}
