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
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samual
 */
public class VideoAccessServiceImpl implements VideoAccessService {

    @Autowired
    private VideoAccessDaoImpl videoAccessDaoImpl;

    /**
     *
     * @return
     */
    public VideoAccessDaoImpl getVideoAccessDaoImpl() {
        return videoAccessDaoImpl;
    }

    /**
     *
     * @param videoAccessDaoImpl
     */
    public void setVideoAccessDaoImpl(VideoAccessDaoImpl videoAccessDaoImpl) {
        this.videoAccessDaoImpl = videoAccessDaoImpl;
    }

    /**
     *
     * @param uuid
     * @return
     * @throws AccountNotActivatedException
     */
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

    /**
     *
     * @param videoInfoId
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public VideoInfo getVideoInfo(int videoInfoId, int uuid) throws DataAccessException, AccountNotActivatedException {
        Session session = this.getVideoAccessDaoImpl().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if (this.isActivated(uuid)) {
            return this.videoAccessDaoImpl.getVideoInfo(videoInfoId, uuid);
        }
        throw new DataAccessException("Incorrect activation key!");
    }

    /**
     *
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public List<VideoInfo> getVideoInfoList(int uuid) throws DataAccessException, AccountNotActivatedException {
        Session session = this.getVideoAccessDaoImpl().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if (this.isActivated(uuid)) {
            return this.videoAccessDaoImpl.getVideoInfoList(uuid);
        }
        throw new DataAccessException("Invalid activation key!");
    }
}
