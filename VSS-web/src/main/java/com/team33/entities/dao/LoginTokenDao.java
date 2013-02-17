package com.team33.entities.dao;

import java.util.List;

import com.team33.entities.LoginToken;
import org.springframework.dao.DataAccessException;

public interface LoginTokenDao {

	  public LoginToken getLoginToken(Integer accountId) throws DataAccessException;

	  public void saveLoginToken(LoginToken token) throws DataAccessException;
          
          public void removeLoginToken(Integer accountID);
}
