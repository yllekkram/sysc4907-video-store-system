/*
 * This interface will establish methods  responsible for the system admin's data access
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
import com.team33.services.exception.DataAccessException;

/**
 *
 * @author Samual
 */
public interface AdminDao {

    public void addVideoInfo(VideoInfo videoInfo) throws DataAccessException;

    public void removeVideoInfo(int videoInfoId)throws DataAccessException;

    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException;
}
