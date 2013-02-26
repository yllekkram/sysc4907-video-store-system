package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.Account;
import com.team33.entities.LoginToken;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.team33.services.exception.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LaFamiglia
 */
@Repository
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao, LoginTokenDao {

    private static final int FIRST = 0;
    //tells Spring to inject the dependency
    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @return
     * @throws DataAccessException
     */
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

    /**
     *
     * @param accountId
     * @return
     * @throws DataAccessException
     */
    @Override
    public Account getAccount(Integer accountId) throws DataAccessException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        return (Account) curSession.get(Account.class,
                accountId);
    }

    /**
     *
     * @param username
     * @return
     * @throws DataAccessException
     */
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
    
    /**
     *
     * @param accountID
     * @return
     */
    @Override
    public LoginToken getLoginToken(Integer accountID) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query tokenQuery;
        try {
            if (curSession == null) {
                throw new DataAccessException("Data Access invalid");
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return null;
        }
        tokenQuery = curSession.getNamedQuery("LoginToken.findByAccountid");
        tokenQuery.setParameter("accountid", accountID);
        if (tokenQuery.list().isEmpty()) {
            return null;
        }
        return (LoginToken) tokenQuery.list().get(FIRST);
    }

    /**
     *
     * @param account
     * @throws DataAccessException
     */
    @Override
    public void saveAccount(Account account) throws DataAccessException {        
        HibernateTemplate hibTemp = this.getHibernateTemplate();
        hibTemp.saveOrUpdate(account);
    }

    /**
     *
     * @param token
     * @throws DataAccessException
     */
    @Override
    public void saveLoginToken(LoginToken token) throws DataAccessException {        
        HibernateTemplate hibTemp = this.getHibernateTemplate();
        hibTemp.saveOrUpdate(token);
    }

    /**
     *
     * @param accountId
     */
    @Override
    public void removeAccount(Integer accountId) {
        Account account = (Account) sessionFactory.getCurrentSession().load(
                Account.class, accountId);
        if (null != account) {
            sessionFactory.getCurrentSession().delete(account);
        }
    }

    /**
     *
     * @param accountId
     */
    @Override
    public void removeLoginToken(Integer accountId) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query tokenQuery;
        try {
            if (curSession == null) {
                throw new DataAccessException("Data Access invalid");
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
        }
        tokenQuery = curSession.getNamedQuery("LoginToken.findByAccountid");
        tokenQuery.setParameter("accountid", accountId);
        if (tokenQuery.list().isEmpty()) {
            LoginToken token = (LoginToken) tokenQuery.list().get(FIRST);
            sessionFactory.getCurrentSession().delete(token.getLogintokenPK());
            sessionFactory.getCurrentSession().delete(token);
        }
    }
}
