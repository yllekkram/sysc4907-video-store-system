package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.LoginToken;
import org.springframework.dao.DataAccessException;

/**
 * Dictates the data access methods for a login token
 *
 * @author Samual
 */
public interface LoginTokenDao {

    /**
     * Retrieves the login token
     *
     * @param accountId
     * @return LoginToken
     * @throws DataAccessException
     */
    public LoginToken getLoginToken(Integer accountId) throws DataAccessException;

    /**
     * Persists the login token
     *
     * @param token
     * @throws DataAccessException
     */
    public void saveLoginToken(LoginToken token) throws DataAccessException;

    /**
     * Removes the login token
     *
     * @param accountID
     */
    public void removeLoginToken(Integer accountID);
}
