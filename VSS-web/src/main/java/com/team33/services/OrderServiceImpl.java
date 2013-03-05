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
 * Provides the services required for order processing videos
 *
 * @author Samual
 */
public class OrderServiceImpl implements OrderService {

    //tells Spring to inject the dependency
    @Autowired
    private OrdersDaoImpl ordersDaoImpl;
    @Autowired
    private CreditCardValidator creditCardValidator;

    /**
     * Sets the stub creditCardValidator to the provided creditCardValiditor
     *
     * @param creditCardValidator
     */
    public void setCreditCardValidator(CreditCardValidator creditCardValidator) {
        this.creditCardValidator = creditCardValidator;
    }

    /**
     * Retrieves the currently used creditCardValiditor to provide credit card
     * functionality
     *
     * @return CreditCardValidator
     */
    public CreditCardValidator getCreditCardValidator() {
        return creditCardValidator;
    }

    /**
     * Sets the implemented order dao to the provided implementation of the
     * order dao
     *
     * @param dao
     */
    public void setOrdersDaoImpl(OrdersDaoImpl dao) {
        this.ordersDaoImpl = dao;
    }

    /**
     * Retrieves the current implemented order dao
     *
     * @return OrderDaoImpl
     */
    public OrdersDaoImpl getOrdersDaoImpl() {
        return this.ordersDaoImpl;
    }

    /**
     * Used to determine whether or not the provided login token corresponds to
     * an active account
     *
     * @param uuid
     * @return boolean
     * @throws AccountNotActivatedException
     */
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

    /**
     * Generates an id for purchase based on the order and account info
     *
     * @param orderId
     * @param accountId
     * @return Integer
     * @throws AccountNotActivatedException
     */
    public int genPurchaseId(int orderId, int accountId) {
        int hash = 0;
        hash += (int) orderId;
        hash += (int) accountId;
        return hash;
    }

    /**
     * Generates an id for purchase based on the order, account info and
     * expiryDate
     *
     * @param orderId
     * @param accountId
     * @param rentalExpiryDate
     * @return Integer
     */
    public int genRentalId(int orderId, int accountId, Date rentalExpiryDate) {
        int hash = 0;
        hash += (int) orderId;
        hash += (int) accountId;
        hash += (rentalExpiryDate != null ? rentalExpiryDate.hashCode() : 0);
        return hash;
    }

    /**
     * Persists a new purchase in the system provided that the videoInfoId,
     * orderId, and login token id are given
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public void addPurchase(Integer videoInfoId, Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Session session = this.getOrdersDaoImpl().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Purchase purchase = new Purchase(genPurchaseId(orderId, this.getOrdersDaoImpl().getOrder(orderId).getAccount().getId()), orderId, this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().getAccountid(), videoInfoId);
            int newPrice = this.getOrdersDaoImpl().getOrder(orderId).getPendingCharge() + purchase.getVideoInfo().getPurchasePrice();
            this.getOrdersDaoImpl().getOrder(orderId).setPendingCharge(newPrice);
            this.getOrdersDaoImpl().savePurchase(this.getOrdersDaoImpl().getOrder(orderId), purchase);
        }
    }

    /**
     * Persists a new rental in the system provided that videoInfoId, orderId,
     * login token id, and the rental expiration date is given
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @param rentalExpiryDate
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public void addRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Session session = this.getOrdersDaoImpl().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Rental rental = new Rental(genRentalId(orderId, this.getOrdersDaoImpl().getOrder(orderId).getAccount().getId(), rentalExpiryDate), videoInfoId, orderId, this.getOrdersDaoImpl().getOrder(orderId).getOrdersPK().getAccountid(), rentalExpiryDate);
            int newPrice = this.getOrdersDaoImpl().getOrder(orderId).getPendingCharge() + rental.getVideoInfo().getPurchasePrice();
            this.getOrdersDaoImpl().getOrder(orderId).setPendingCharge(newPrice);
            this.getOrdersDaoImpl().saveRental(this.getOrdersDaoImpl().getOrder(orderId), rental);
        }
    }

    /**
     * Determines if payment can be processed for a given order based on the
     * orderId, login token id, the validation number, and the totalCost
     *
     * @param orderId
     * @param uuid
     * @param validationNum
     * @param totalCost
     * @throws AccountNotActivatedException
     * @throws PaymentException
     * @throws InsufficientFundsException
     */
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
                    //Persists the order after payment is confirmed
                    Orders transactionOrder = new Orders(orderId, this.getOrdersDaoImpl().getLoginToken(uuid).getAccount().getId());
                    this.getOrdersDaoImpl().saveOrder(transactionOrder);
                    this.getCreditCardValidator().charge();
                }
            }
        }
    }

    /**
     * Retrieves an order provided the orderID, and login token id are given
     *
     * @param orderId
     * @param uuid
     * @return Orders
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public Orders getOrder(Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.getOrdersDaoImpl().getOrder(orderId);
        }
        throw new DataAccessException("Incorrect activation key!");
    }

    /**
     * Retrieves a list of orders provided that the login token id is given
     *
     * @param uuid
     * @return List<Orders>
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public List<Orders> getOrders(int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.getOrdersDaoImpl().getOrders(this.getOrdersDaoImpl().getLoginToken(uuid));
        }

        throw new DataAccessException("Incorrect Activation key");
    }

    /**
     * Remove an order with orderId, from user with login token id
     *
     * @param orderID
     * @param uuid
     * @throws AccountNotActivatedException
     */
    @Override
    public void removeOrder(Integer orderID, int uuid) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            this.ordersDaoImpl.removeOrder(orderID);
        }
    }

    /**
     * Removes a purchase from the order
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @throws AccountNotActivatedException
     */
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

    /**
     * Removes a rental from the order
     *
     * @param videoInfoId
     * @param orderId
     * @param uuid
     * @param rentalExpiryDate
     * @throws AccountNotActivatedException
     */
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
