package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.Orders;
import com.team33.entities.Purchase;
import com.team33.entities.Rental;
import com.team33.entities.dao.AccountDao;
import com.team33.entities.dao.BrowseDao;
import com.team33.entities.dao.OrdersDao;
import com.team33.services.exception.*;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides the services required for order processing videos
 *
 * @author Samual
 */
@Service
public class OrderServiceImpl implements OrderService {

    //tells Spring to inject the dependency
    @Autowired
    private OrdersDao ordersDao;
    
    @Autowired
    private BrowseDao browseDao;
    
    @Autowired
    private AccountDao accountDao;
    
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
    public void setOrdersDao(OrdersDao dao) {
        this.ordersDao = dao;
    }

    /**
     * Retrieves the current implemented order dao
     *
     * @return OrderDao
     */
    public OrdersDao getOrdersDao() {
        return this.ordersDao;
    }

    /**
     * Used to determine whether or not the provided login token corresponds to
     * an active account
     *
     * @param uuid
     * @return boolean
     * @throws AccountNotActivatedException
     */
    @Transactional
    public boolean isActivated(int uuid) throws AccountNotActivatedException {
        try {
            LoginToken loginToken = this.getOrdersDao().getLoginToken(uuid);

            if (!loginToken.getAccount().getActivated()) {
                throw new AccountNotActivatedException("Account Inactive");
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return false;
        } catch (AccountNotActivatedException ae) {
            ae.printStackTrace();
            return false;
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
        hash += (int) System.currentTimeMillis();
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
    @Transactional
    public void addPurchase(Integer videoInfoId, Orders order, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Integer orderID = order.getOrdersPK().getId();
            Integer accountID = order.getOrdersPK().getAccountid();
            Purchase purchase = new Purchase(genPurchaseId(orderID, accountID), orderID, accountID, videoInfoId);
            // This is just asking for inconsistencies, but hey, this isn't my layer.
            purchase.setVideoInfo(browseDao.displayVideoDetails(videoInfoId));
            
            int purchasePrice = purchase.getVideoInfo().getPurchasePrice();
            int newPrice = order.getPendingCharge() + purchasePrice;
            order.setPendingCharge(newPrice);
            this.getOrdersDao().savePurchase(order, purchase);
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
    @Transactional
    public void addRental(Integer videoInfoId, Orders order, int uuid, Date rentalExpiryDate) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Integer orderId = order.getOrdersPK().getId();
            Integer accountId = order.getOrdersPK().getId();
            Rental rental = new Rental(genRentalId(orderId, accountId, rentalExpiryDate), videoInfoId, orderId, accountId, rentalExpiryDate);
            rental.setVideoInfo(browseDao.displayVideoDetails(videoInfoId));
            
            int newPrice = order.getPendingCharge() + rental.getVideoInfo().getPurchasePrice();
            order.setPendingCharge(newPrice);
            this.getOrdersDao().saveRental(order, rental);
        }
    }

    /**
     * Determines if payment can be processed for a given order based on the
     * orderId, login token id, the validation number, and the totalCost
     *
     * @param order
     * @param uuid
     * @param validationNum
     * @param totalCost
     * @throws AccountNotActivatedException
     * @throws PaymentException
     * @throws InsufficientFundsException
     */
    @Override
    @Transactional
    public void confirmPayment(Orders order, int uuid, int validationNum, int totalCost) throws AccountNotActivatedException, PaymentException, InsufficientFundsException {
        if (this.isActivated(uuid)) {
            if (this.getCreditCardValidator().isCardValid(validationNum)) {
                //accumulate charges for an account
                int allCharges = 0;
                for (Orders o : this.getOrdersDao().getOrders(this.getOrdersDao().getLoginToken(uuid))) {
                    allCharges += o.getPendingCharge();
                }
                //if the charge can be processed create an invoice for the customer and charge him
                if (this.getCreditCardValidator().isChargeValid(totalCost) && this.getCreditCardValidator().isUnderLOC(allCharges, totalCost)) {
                    //Persists the order after payment is confirmed
                    /* Why!!!
                     * Orders transactionOrder = new Orders(order.getOrdersPK().getId(), this.getOrdersDao().getLoginToken(uuid).getAccount().getId());
                     */
                    this.getOrdersDao().saveOrder(order);
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
    @Transactional
    public Orders getOrder(Integer orderId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.getOrdersDao().getOrder(orderId, accountDao.getAccountByLoginToken(uuid).getId());
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
    @Transactional
    public List<Orders> getOrders(int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.getOrdersDao().getOrders(this.getOrdersDao().getLoginToken(uuid));
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
    @Transactional
    public void removeOrder(Integer orderID, int uuid) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            this.ordersDao.removeOrder(orderID);
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
    @Transactional
    public void removePurchase(Integer videoInfoId, Integer orderId, int uuid) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Integer accountID = accountDao.getAccountByLoginToken(uuid).getId();
            Orders order = ordersDao.getOrder(orderId, accountID);
            Purchase purchase = new Purchase((int) Math.random(), orderId, order.getOrdersPK().getAccountid(), videoInfoId);
            
            int newPrice = order.getPendingCharge() - purchase.getVideoInfo().getPurchasePrice();
            order.setPendingCharge(newPrice);
            this.getOrdersDao().removePurchase(order, purchase);

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
    @Transactional
    public void removeRental(Integer videoInfoId, Integer orderId, int uuid, Date rentalExpiryDate) throws AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            Integer accountID = accountDao.getAccountByLoginToken(uuid).getId();
            Orders order = ordersDao.getOrder(orderId, accountID);
            Rental rental = new Rental((int) Math.random(), videoInfoId, orderId, order.getOrdersPK().getAccountid(), rentalExpiryDate);
            
            int newPrice = order.getPendingCharge() - rental.getVideoInfo().getPurchasePrice();
            order.setPendingCharge(newPrice);
            this.getOrdersDao().removeRental(order, rental);
        }
    }

    @Override
    @Transactional
    public void saveOrder(Orders order) {
        ordersDao.saveOrder(order);
    }

    @Override
    public void saveOrUpdateOrder(Orders order) {
        ordersDao.saveOrUpdateOrder(order);
    }
}
