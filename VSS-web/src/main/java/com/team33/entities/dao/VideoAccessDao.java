/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;

/**
 *
 * @author Mark,Samual
 */
public interface VideoAccessDao {
    public List<VideoInfo> getVideoInfoList(LoginToken loginToken) throws DataAccessException,AccountNotActivatedException;
    
    public VideoInfo getVideoInfo(int videoInfoId,LoginToken loginToken) throws DataAccessException,AccountNotActivatedException;
    
}