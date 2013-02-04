/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.Order1;
import com.team33.entities.Purchase;
import com.team33.entities.Rental;
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
    
    @Autowired
    private CreditCardValidator creditCardValidator;

    public void setCreditCardValidator(CreditCardValidator creditCardValidator) {
        this.creditCardValidator = creditCardValidator;
    }

    public CreditCardValidator getCreditCardValidator() {
        return creditCardValidator;
    }

    public void setOrder1DaoImpl(Order1DaoImpl dao) {
        this.order1DaoImpl = dao;
    }

    public Order1DaoImpl getOrder1DaoImpl() {
        return this.order1DaoImpl;
    }

    public void isActivated(LoginToken loginToken) throws AccountNotActivatedException {
        if (!loginToken.getAccount().getActivated()) {
            throw new AccountNotActivatedException("Account Inactive");
        }
    }

    @Override
    public void addPurchase(Integer videoInfoId, Integer orderId, LoginToken loginToken) throws DataAccessException, AccountNotActivatedException {
        this.isActivated(loginToken);
        Purchase purchase = new Purchase(this.getOrder1DaoImpl().getOrder(orderId).getAccount().getId(),
                orderId, videoInfoId);
        this.getOrder1DaoImpl().getOrder(orderId).getOrder1PK().increaseCharge(purchase.getVideoInfo().getPurchasePrice());
        this.getOrder1DaoImpl().savePurchase(this.getOrder1DaoImpl().getOrder(orderId), purchase);
    }

    @Override
    public void addRental(Integer videoInfoId, Integer orderId, LoginToken loginToken) throws DataAccessException, AccountNotActivatedException {
        this.isActivated(loginToken);
        Rental rental = new Rental(this.getOrder1DaoImpl().getOrder(orderId).getAccount().getId(),
                orderId, videoInfoId);
        this.getOrder1DaoImpl().getOrder(orderId).getOrder1PK().increaseCharge(rental.getVideoInfo().getPurchasePrice());
        this.getOrder1DaoImpl().saveRental(this.getOrder1DaoImpl().getOrder(orderId), rental);
    }

    @Override
    public void confirmPayment(Integer orderId, LoginToken loginToken, int validationNum, int totalCost) throws AccountNotActivatedException, PaymentException, InsufficientFundsException {
        this.isActivated(loginToken);
        if (this.getCreditCardValidator().isCardValid(validationNum)) {
            //accumulate charges for an account
            int allCharges = 0;
            for (int i = 0; i < this.getOrder1DaoImpl().getOrders(loginToken).size(); i++) {
                allCharges += this.getOrder1DaoImpl().getOrders(loginToken).get(i).getOrder1PK().getPendingCharge();
            }
            //if the charge can be processed create an invoice for the customer and charge him
            if (this.getCreditCardValidator().isChargeValid(totalCost) && this.getCreditCardValidator().isUnderLOC(allCharges, totalCost)) {
                this.getOrder1DaoImpl().createInvoice(this.getOrder(orderId, loginToken));
                this.getCreditCardValidator().charge();
            }
        }

    }

    @Override
    public Order1 getOrder(Integer orderId, LoginToken loginToken) throws DataAccessException, AccountNotActivatedException {
        this.isActivated(loginToken);
        return this.getOrder1DaoImpl().getOrder(orderId);
    }

    @Override
    public List<Order1> getOrders(LoginToken loginToken) throws DataAccessException, AccountNotActivatedException {
        this.isActivated(loginToken);
        return this.getOrder1DaoImpl().getOrders(loginToken);
    }

    @Override
    public void removeOrder(Integer orderID, LoginToken loginToken) throws AccountNotActivatedException {
        this.isActivated(loginToken);
        this.order1DaoImpl.removeOrder(orderID);
    }

    //Removes a purchase from the order
    @Override
    public void removePurchase(Integer videoInfoId, Integer orderId, LoginToken loginToken) throws AccountNotActivatedException {
        this.isActivated(loginToken);
        Purchase purchase = new Purchase(this.getOrder1DaoImpl().getOrder(orderId).getAccount().getId(),
                orderId, videoInfoId);
        this.getOrder1DaoImpl().getOrder(orderId).getOrder1PK().
                decreaseCharge(purchase.getVideoInfo().getPurchasePrice());
        this.getOrder1DaoImpl().removePurchase(this.getOrder1DaoImpl().getOrder(orderId), purchase);
    }

    //Removes a rental from the order
    @Override
    public void removeRental(Integer videoInfoId, Integer orderId, LoginToken loginToken) throws AccountNotActivatedException {
        this.isActivated(loginToken);
        Rental rental = new Rental(this.getOrder1DaoImpl().getOrder(orderId).getAccount().getId(),
                orderId, videoInfoId);
        this.getOrder1DaoImpl().getOrder(orderId).getOrder1PK().
                decreaseCharge(rental.getVideoInfo().getRentalPrice());
        this.getOrder1DaoImpl().removeRental(this.getOrder1DaoImpl().getOrder(orderId), rental);
    }
}
