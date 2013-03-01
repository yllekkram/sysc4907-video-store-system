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

    /**
     *
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public List<VideoInfo> getVideoInfoList(int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     *
     * @param videoInfoId
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    public VideoInfo getVideoInfo(int videoInfoId, int uuid) throws DataAccessException, AccountNotActivatedException;

    /**
     *
     * @param uuid
     * @return
     * @throws DataAccessException
     */
    public LoginToken getLoginToken(int uuid) throws DataAccessException;
}
