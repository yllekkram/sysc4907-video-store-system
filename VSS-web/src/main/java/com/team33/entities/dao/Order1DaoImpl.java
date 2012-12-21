package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.Order1;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class Order1DaoImpl implements Order1Dao {

    
        @Autowired
        private SessionFactory sessionFactory ;
     
	@Override
	public List<Order1> getOrders() throws DataAccessException {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery
                        ("Active Video Orders").list();
	}

	@Override
	public Order1 getOrder(Long orderId) throws DataAccessException {
		return (Order1)sessionFactory.getCurrentSession().get(Order1.class, orderId);
	}

	@Override
	public void saveOrder(Order1 order) throws DataAccessException {
		sessionFactory.getCurrentSession().save(order);
		
	}
        
        @Override
        public void removeOrder(Long orderId) {
        Order1 order = (Order1) sessionFactory.getCurrentSession().load(
                Order1.class, orderId);
        if (null != order) {
            sessionFactory.getCurrentSession().delete(order);
        }
 
    }
}
