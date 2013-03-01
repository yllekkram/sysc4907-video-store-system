/*
 * This interface will establish methods to  access the videos ordered by an account
 */
package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;

/**
 *
 * @author Caleb,Samual
 */
public interface VideoAccessService {

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
}
