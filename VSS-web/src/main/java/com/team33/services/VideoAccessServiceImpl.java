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

    public VideoAccessDaoImpl getVideoAccessDaoImpl() {
        return videoAccessDaoImpl;
    }

    public void setVideoAccessDaoImpl(VideoAccessDaoImpl videoAccessDaoImpl) {
        this.videoAccessDaoImpl = videoAccessDaoImpl;
    }

    public boolean isActivated(int uuid) throws AccountNotActivatedException {
        try {
            LoginToken loginToken = this.getVideoAccessDaoImpl().getLoginToken(uuid);

            if (!loginToken.getAccount().getActivated()) {
                throw new AccountNotActivatedException("Account Inactive");
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
        } catch (AccountNotActivatedException ae) {
            ae.printStackTrace();
        }
        return true;
    }

    @Override
    public VideoInfo getVideoInfo(int videoInfoId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.videoAccessDaoImpl.getVideoInfo(videoInfoId, uuid);
        }
        throw new DataAccessException("Incorrect activation key!");
    }

    @Override
    public List<VideoInfo> getVideoInfoList(int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.videoAccessDaoImpl.getVideoInfoList(uuid);
        }
        throw new DataAccessException("Invalid activation key!");
    }
}
