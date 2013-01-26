package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.Order1;
import com.team33.services.exception.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class Order1DaoImpl extends HibernateDaoSupport implements Order1Dao {
    //tells Spring to inject the dependency
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    /*Find all orders of a particular account that is activated*/
    public List<Order1> getOrders(Integer orderId) throws DataAccessException, AccountNotActivatedException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query orderQuery;
        orderQuery = curSession.getNamedQuery("Order1.findByActiveAccount");
        orderQuery.setParameter("accountid", this.getOrder(orderId).getAccount().getId());
        orderQuery.setParameter("accountActivated", this.getOrder(orderId).getAccount().getActivated());
        return orderQuery.list();
    }

    @Override
    public Order1 getOrder(Integer orderId) throws DataAccessException, AccountNotActivatedException {
        return (Order1) sessionFactory.getCurrentSession().get(Order1.class, orderId);
    }

    @Override
    public void saveOrder(Order1 order) throws DataAccessException {
        sessionFactory.getCurrentSession().save(order);

    }

    @Override
    public void removeOrder(Integer orderId) throws AccountNotActivatedException {
        Order1 order = (Order1) sessionFactory.getCurrentSession().load(
                Order1.class, orderId);
        if (null != order) {
            sessionFactory.getCurrentSession().delete(order);
        }

    }  
}
