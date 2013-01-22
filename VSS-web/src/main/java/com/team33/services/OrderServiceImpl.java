/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

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

    @Autowired
    private Order1DaoImpl order1DaoImpl;

    public void setOrder1DaoImpl(Order1DaoImpl dao) {
        this.order1DaoImpl = dao;
    }

    public Order1DaoImpl getOrder1daoImpl() {
        return this.order1DaoImpl;
    }

    @Override
    public void addOrder(Order1 order) throws DataAccessException {
    }

    @Override
    public void confirmPayment(Integer orderId) throws AccountNotActivatedException, LoginException {
        if (!this.getOrder(orderId).getOrder1PK().getAccountActivated()) {
            throw new AccountNotActivatedException("Please activate the account before ordering videos.");
        }
    }

    @Override
    public Order1 getOrder(Integer orderId) throws DataAccessException, AccountNotActivatedException, LoginException {
        if (!this.getOrder(orderId).getOrder1PK().getAccountActivated()) {
            throw new AccountNotActivatedException("Please activate the account before ordering videos.");
        }
        return this.getOrder(orderId);
    }

    @Override
    public List<Order1> getOrders(Integer accountId, Boolean accountActivated) throws DataAccessException, AccountNotActivatedException, LoginException {
        return this.order1DaoImpl.getOrders(accountId, accountActivated);
    }

    @Override
    public void removeOrder(Integer orderID) {
        this.order1DaoImpl.removeOrder(orderID);
    }

    @Override
    /*
     * Clears all orders from a users session
     */
    public void removeOrders(Integer accountID, Boolean accountActivated) throws AccountNotActivatedException, LoginException {
        this.order1DaoImpl.removeOrders(accountID, accountActivated);
    }
}
