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
import com.team33.services.exception.*;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samual
 */
public class OrderServiceImpl implements OrderService {

    //tells Spring to inject the dependency
    @Autowired
    private OrdersDaoImpl ordersDaoImpl;
    @Autowired
    private CreditCardValidator creditCardValidator;

    public void setCreditCardValidator(CreditCardValidator creditCardValidator) {
        this.creditCardValidator = creditCardValidator;
    }

    public CreditCardValidator getCreditCardValidator() {
        return creditCardValidator;
    }

    public void setOrdersDaoImpl(OrdersDaoImpl dao) {
        this.ordersDaoImpl = dao;
    }

    public OrdersDaoImpl getOrdersDaoImpl() {
        return this.ordersDaoImpl;
    }

    public boolean isActivated(int uuid) throws AccountNotActivatedException {
        try {
            LoginToken loginToken = this.getOrdersDaoImpl().getLoginToken(uuid);

            if (!loginToken.getAccount().getActivated()) {
                throw new AccountNotActivatedException("Account Inactive");
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
        } catch (AccountNotActivatedException ae) {
            ae.printStackTrace();
        }
        return true;
    }

    @Override
    public void addPurchase(Integer videoInfoId, Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Session session = this.getOrdersDaoImpl().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Purchase purchase = new Purchase((int) Math.random(), orderId, this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().getAccountid(), videoInfoId);
            int newPrice = this.getOrdersDaoImpl().getOrder(orderId).getPendingCharge() + purchase.getVideoInfo().getPurchasePrice();
            this.getOrdersDaoImpl().getOrder(orderId).setPendingCharge(newPrice);
            this.getOrdersDaoImpl().savePurchase(this.getOrdersDaoImpl().getOrder(orderId), purchase);
        }
    }

    @Override
    public void addRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Session session = this.getOrdersDaoImpl().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Rental rental = new Rental((int) Math.random(), videoInfoId, orderId, this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().getAccountid(), rentalExpiryDate);
            int newPrice = this.getOrdersDaoImpl().getOrder(orderId).getPendingCharge() + rental.getVideoInfo().getPurchasePrice();
            this.getOrdersDaoImpl().getOrder(orderId).setPendingCharge(newPrice);
            this.getOrdersDaoImpl().saveRental(this.getOrdersDaoImpl().getOrder(orderId), rental);
        }
    }

    @Override
    public void confirmPayment(Integer orderId, int uuid, int validationNum, int totalCost) throws AccountNotActivatedException, PaymentException, InsufficientFundsException {
        if (this.isActivated(uuid)) {
            if (this.getCreditCardValidator().isCardValid(validationNum)) {
                //accumulate charges for an account
                int allCharges = 0;
                for (int i = 0; i < this.getOrdersDaoImpl().getOrders(this.getOrdersDaoImpl().getLoginToken(uuid)).size(); i++) {
                    allCharges += this.getOrdersDaoImpl().getOrders(this.getOrdersDaoImpl().getLoginToken(uuid)).get(i).getPendingCharge();
                }
                //if the charge can be processed create an invoice for the customer and charge him
                if (this.getCreditCardValidator().isChargeValid(totalCost) && this.getCreditCardValidator().isUnderLOC(allCharges, totalCost)) {
                    Orders transactionOrder = new Orders(orderId, this.getOrdersDaoImpl().getLoginToken(uuid).getAccount().getId());
                    this.getOrdersDaoImpl().saveOrder(transactionOrder);
                    this.getCreditCardValidator().charge();
                }
            }
        }
    }

    @Override
    public Orders getOrder(Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.getOrdersDaoImpl().getOrder(orderId);
        }
        throw new DataAccessException("Incorrect activation key!");
    }

    @Override
    public List<Orders> getOrders(int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.getOrdersDaoImpl().getOrders(this.getOrdersDaoImpl().getLoginToken(uuid));
        }

        throw new DataAccessException("Incorrect Activation key");
    }

    @Override
    public void removeOrder(Integer orderID, int uuid) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            this.ordersDaoImpl.removeOrder(orderID);
        }
    }

    //Removes a purchase from the order
    @Override
    public void removePurchase(Integer videoInfoId, Integer orderId, int uuid) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Session session = this.getOrdersDaoImpl().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Purchase purchase = new Purchase((int) Math.random(), orderId, this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().getAccountid(), videoInfoId);
            int newPrice = this.getOrdersDaoImpl().getOrder(orderId).getPendingCharge() - purchase.getVideoInfo().getPurchasePrice();
            this.getOrdersDaoImpl().getOrder(orderId).setPendingCharge(newPrice);
            this.getOrdersDaoImpl().removePurchase(this.getOrdersDaoImpl().getOrder(orderId), purchase);

        }
    }
    //Removes a rental from the order

    @Override
    public void removeRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Session session = this.getOrdersDaoImpl().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Rental rental = new Rental((int) Math.random(), videoInfoId, orderId, this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().getAccountid(), rentalExpiryDate);
            int newPrice = this.getOrdersDaoImpl().getOrder(orderId).getPendingCharge() - rental.getVideoInfo().getPurchasePrice();
            this.getOrdersDaoImpl().getOrder(orderId).setPendingCharge(newPrice);
            this.getOrdersDaoImpl().removeRental(this.getOrdersDaoImpl().getOrder(orderId), rental);
        }
    }
}
