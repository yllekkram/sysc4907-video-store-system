package com.team33.entities.dao;

import com.team33.entities.*;
import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class Order1DaoImpl extends HibernateDaoSupport implements Order1Dao {
    //tells Spring to inject the dependency

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    /*
     * Find all orders of a particular account that is activated
     */
    public List<Order1> getOrders(LoginToken loginToken) throws DataAccessException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query orderQuery;
        orderQuery = curSession.getNamedQuery("Order1.findByActiveAccount");
        orderQuery.setParameter("accountid", loginToken.getAccount().getId());
        return orderQuery.list();
    }

    @Override
    public Order1 getOrder(Integer orderId) throws DataAccessException {
        return (Order1) sessionFactory.getCurrentSession().get(Order1.class, orderId);
    }

    @Override
    public void saveOrder(Order1 order) throws DataAccessException {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public void removeOrder(Integer orderId) {
        Order1 order = (Order1) sessionFactory.getCurrentSession().load(
                Order1.class, orderId);
        if (null != order) {
            sessionFactory.getCurrentSession().delete(order);
        }

    }

    @Override
    public void createInvoice(Order1 order) throws DataAccessException {
        Invoice invoice = new Invoice(order.getAccount().getId(), order.getOrder1PK().getId(),
                order.getOrder1PK().getPendingCharge());
        if (null != invoice) {
            sessionFactory.getCurrentSession().save(invoice);
        }
    }

    @Override
    public void removePurchase(Order1 order, Purchase purchase) throws DataAccessException {
        if (order != null) {
            if (purchase != null) {
                sessionFactory.getCurrentSession().delete(purchase);
            }
        }
    }

    @Override
    public void removeRental(Order1 order, Rental rental) throws DataAccessException {
        if (order != null) {
            if (rental != null) {
                sessionFactory.getCurrentSession().delete(rental);
            }
        }
    }

    @Override
    public void savePurchase(Order1 order, Purchase purchase) throws DataAccessException {
        if (order != null) {
            if (purchase != null) {
                sessionFactory.getCurrentSession().save(purchase);
            }
        }
    }

    @Override
    public void saveRental(Order1 order, Rental rental) throws DataAccessException {
        if (order != null) {
            if (rental != null) {
                sessionFactory.getCurrentSession().save(rental);
            }
        }
    }
}
