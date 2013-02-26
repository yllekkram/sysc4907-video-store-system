package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.LoginToken;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author LaFamiglia
 */
public interface LoginTokenDao {

	  /**
     *
     * @param accountId
     * @return
     * @throws DataAccessException
     */
    public LoginToken getLoginToken(Integer accountId) throws DataAccessException;

	  /**
     *
     * @param token
     * @throws DataAccessException
     */
    public void saveLoginToken(LoginToken token) throws DataAccessException;
          
          /**
     *
     * @param accountID
     */
    public void removeLoginToken(Integer accountID);
}
