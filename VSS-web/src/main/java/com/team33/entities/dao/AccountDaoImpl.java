package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.Account;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    
        @Autowired
        private SessionFactory sessionFactory ;
        
	@Override
	public List<Account> getAccounts() throws DataAccessException {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("active"
                        + " Account").list();
	}

	@Override
	public Account getAccount(Long accountId) throws DataAccessException {
		// TODO Auto-generated method stub
		return (Account)sessionFactory.getCurrentSession().get(Account.class, 
                        accountId);
	}

	@Override
	public void saveAccount(Account account) throws DataAccessException {
		sessionFactory.getCurrentSession().save(account);
		
	}
        
        @Override
        public void removeAccount(Long accountId) {
        Account account = (Account) sessionFactory.getCurrentSession().load(
                Account.class, accountId);
        if (null != account) {
            sessionFactory.getCurrentSession().delete(account);
        }
 
    }

}
