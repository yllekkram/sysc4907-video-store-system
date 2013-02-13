package com.team33.entities.dao;

import com.team33.entities.Account;
import java.util.List;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.team33.services.exception.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao {

    private static final int FIRST = 0;
    //tells Spring to inject the dependency
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    /*
     * Finds all accounts and returns the list of accounts
     */
    public List<Account> getAccounts() throws DataAccessException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query accountQuery;
        accountQuery = curSession.getNamedQuery("Account.findAll");
        return accountQuery.list();
    }

    @Override
    public Account getAccount(Integer accountId) throws DataAccessException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        return (Account) curSession.get(Account.class,
                accountId);
    }

    @Override
    public Account getAccount(String username) throws DataAccessException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query accountQuery;
        try {
            if (curSession == null) {
                throw new DataAccessException("Data Access invalid");
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return null;
        }
        accountQuery = curSession.getNamedQuery("Account.findByName");
        accountQuery.setParameter("name", username);
        if (accountQuery.list().isEmpty()) {
            return null;
        }
        return (Account) accountQuery.list().get(FIRST);



    }

    @Override
    public void saveAccount(Account account) throws DataAccessException {        
        HibernateTemplate hibTemp = this.getHibernateTemplate();
        hibTemp.save(account);
    }

    @Override
    public void removeAccount(Integer accountId) {
        Account account = (Account) sessionFactory.getCurrentSession().load(
                Account.class, accountId);
        if (null != account) {
            sessionFactory.getCurrentSession().delete(account);
        }

    }
}
