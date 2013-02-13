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
import java.util.List;
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
//            Purchase purchase = new Purchase(this.getOrdersDaoImpl().getOrder(orderId).getAccount().getId(),
//                    orderId, videoInfoId);
//            this.getOrder1DaoImpl().getOrder(orderId).getOrdersPK().increaseCharge(purchase.getVideoInfo().getPurchasePrice());
//            this.getOrder1DaoImpl().savePurchase(this.getOrdersDaoImpl().getOrder(orderId), purchase);
        }
    }

    @Override
    public void addRental(Integer videoInfoId, Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
//            Rental rental = new Rental(this.getOrdersDaoImpl().getOrder(orderId).getAccount().getId(),
//                    orderId, videoInfoId);
//            this.getOrder1DaoImpl().getOrder(orderId).getOrdersPK().increaseCharge(rental.getVideoInfo().getPurchasePrice());
//            this.getOrder1DaoImpl().saveRental(this.getOrder1DaoImpl().getOrder(orderId), rental);
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
//            return this.getOrder1DaoImpl().getOrder(orderId);
            return null;
        }
        throw new DataAccessException("Incorrect activation key!");
    }

    @Override
    public List<Orders> getOrders(int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
//            return this.getOrder1DaoImpl().getOrders(this.getOrder1DaoImpl().getLoginToken(uuid));
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
//            Purchase purchase = new Purchase(this.getOrdersDaoImpl().getOrder(orderId).getAccount().getId(),
//                    orderId, videoInfoId);
//            this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().
//                    decreaseCharge(purchase.getVideoInfo().getPurchasePrice());
//            this.getOrdersDaoImpl().removePurchase(this.getOrdersDaoImpl().getOrder(orderId), purchase);
        }
    }
    //Removes a rental from the order

    @Override
    public void removeRental(Integer videoInfoId, Integer orderId, int uuid) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
//            Rental rental = new Rental(this.getOrdersDaoImpl().getOrder(orderId).getAccount().getId(),
//                    orderId, videoInfoId);
//            this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().
//                    decreaseCharge(rental.getVideoInfo().getRentalPrice());
//            this.getOrder1DaoImpl().removeRental(this.getOrder1DaoImpl().getOrder(orderId), rental);
        }
    }
}
