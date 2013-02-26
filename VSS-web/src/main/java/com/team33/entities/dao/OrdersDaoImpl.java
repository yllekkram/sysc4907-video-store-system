package com.team33.entities.dao;

import com.team33.entities.Orders;
import com.team33.entities.LoginToken;
import com.team33.entities.Purchase;
import com.team33.entities.Rental;
import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.team33.services.exception.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author LaFamiglia
 */
public class OrdersDaoImpl extends HibernateDaoSupport implements OrdersDao {
    //tells Spring to inject the dependency

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param loginToken
     * @return
     * @throws DataAccessException
     */
    @Override
    /*
     * Find all orders of a particular account that is activated
     */
    public List<Orders> getOrders(LoginToken loginToken) throws DataAccessException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query orderQuery;
        orderQuery = curSession.getNamedQuery("Orders.findByActiveAccount");
        orderQuery.setParameter("accountid", loginToken.getAccount().getId());
        return orderQuery.list();
    }

    /**
     *
     * @param orderId
     * @return
     * @throws DataAccessException
     */
    @Override
    public Orders getOrder(Integer orderId) throws DataAccessException {
        return (Orders) sessionFactory.getCurrentSession().get(Orders.class, orderId);
    }

    /**
     *
     * @param order
     * @throws DataAccessException
     */
    @Override
    public void saveOrder(Orders order) throws DataAccessException {
        sessionFactory.getCurrentSession().save(order);
    }

    /**
     *
     * @param orderId
     */
    @Override
    public void removeOrder(Integer orderId) {
        Orders order = (Orders) sessionFactory.getCurrentSession().load(
                Orders.class, orderId);
        if (null != order) {
            sessionFactory.getCurrentSession().delete(order);
        }

    }

    /**
     *
     * @param order
     * @param purchase
     * @throws DataAccessException
     */
    @Override
    public void removePurchase(Orders order, Purchase purchase) throws DataAccessException {
        if (order != null) {
            if (purchase != null) {
                sessionFactory.getCurrentSession().delete(purchase);
            }
        }
    }

    /**
     *
     * @param order
     * @param rental
     * @throws DataAccessException
     */
    @Override
    public void removeRental(Orders order, Rental rental) throws DataAccessException {
        if (order != null) {
            if (rental != null) {
                sessionFactory.getCurrentSession().delete(rental);
            }
        }
    }

    /**
     *
     * @param order
     * @param purchase
     * @throws DataAccessException
     */
    @Override
    public void savePurchase(Orders order, Purchase purchase) throws DataAccessException {
        if (order != null) {
            if (purchase != null) {
                sessionFactory.getCurrentSession().save(purchase);
            }
        }
    }

    /**
     *
     * @param order
     * @param rental
     * @throws DataAccessException
     */
    @Override
    public void saveRental(Orders order, Rental rental) throws DataAccessException {
        if (order != null) {
            if (rental != null) {
                sessionFactory.getCurrentSession().save(rental);
            }
        }
    }

    /**
     *
     * @param uuid
     * @return
     * @throws DataAccessException
     */
    @Override
    public LoginToken getLoginToken(int uuid) throws DataAccessException {
        if (sessionFactory.getCurrentSession().get(LoginToken.class, uuid) != null) {
            return (LoginToken) sessionFactory.getCurrentSession().get(LoginToken.class, uuid);
        }
        throw new DataAccessException("The activation key is invalid");
    }
}
