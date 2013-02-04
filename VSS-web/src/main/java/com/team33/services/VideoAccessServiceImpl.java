/*
 *  This class will implement methods to  access the videos ordered by an account
 */
package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.entities.dao.VideoAccessDaoImpl;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samual
 */
public class VideoAccessServiceImpl implements VideoAccessService {

    @Autowired
    private VideoAccessDaoImpl videoAccessDaoImpl;

    public void setVideoAccessDaoImpl(VideoAccessDaoImpl videoAccessDaoImpl) {
        this.videoAccessDaoImpl = videoAccessDaoImpl;
    }

    @Override
    public VideoInfo getVideoInfo(int videoInfoId,LoginToken loginToken) throws DataAccessException,AccountNotActivatedException {
        return this.videoAccessDaoImpl.getVideoInfo(videoInfoId,loginToken);
    }

    @Override
    public List<VideoInfo> getVideoInfoList(LoginToken loginToken) throws DataAccessException,AccountNotActivatedException{
        return this.videoAccessDaoImpl.getVideoInfoList(loginToken);
    }

}
