package com.team33.entities.dao;

import com.team33.entities.Status;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class StatusDaoImpl implements StatusDao {

        @Autowired
        private SessionFactory sessionFactory ;
     
	@Override
	public Status getAccountStatus(Long statusId) throws DataAccessException {
		// TODO Auto-generated method stub
		return (Status)sessionFactory.getCurrentSession().get(Status.class, statusId);
	}

	@Override
	public void saveStatus(Status status) throws DataAccessException {
		sessionFactory.getCurrentSession().save(status);
	}

}
