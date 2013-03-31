/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.playback;

import com.team33.entities.LoginToken;
import com.team33.entities.dao.VideoAccessDao;
import com.team33.entities.dao.playback.VideoPlaybackDao;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@Service
public class VideoPlaybackServiceImpl implements VideoPlaybackService{

    @Autowired
    private VideoAccessDao videoAccessDao;
    
    @Autowired
    private VideoPlaybackDao videoPlaybackDao;
    
    public void setVideoAccessDao(VideoAccessDao videoAccessDao) {
        this.videoAccessDao = videoAccessDao;
    }
    
    private VideoAccessDao getVideoAccessDao() {
        return videoAccessDao;
    }
    
    public void setVideoPlaybackDao(VideoPlaybackDao videoPlaybackDao){
        this.videoPlaybackDao = videoPlaybackDao;
    }
    
    /**
     * Takes the unique id of a login token to determine whether an account is
     * active
     *
     * @param uuid
     * @return boolean
     * @throws AccountNotActivatedException
     */
    private boolean isActivated(int uuid) throws AccountNotActivatedException {
        try {
            LoginToken loginToken = this.getVideoAccessDao().getLoginToken(uuid);

            if (!loginToken.getAccount().getActivated()) {
                throw new AccountNotActivatedException("Account Inactive");
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return false;
        } catch (AccountNotActivatedException ae) {
            ae.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    @Override
    @Transactional
    public void saveInformation(int loginToken, int orderId, int videoId, int seconds) {
        try {
            if (!isActivated(loginToken)){
                return;
            }
        } catch (AccountNotActivatedException ex) {
            return;
        }
        LoginToken token = this.videoAccessDao.getLoginToken(loginToken);
        this.videoPlaybackDao.saveVideoPlaybackInformation(orderId, videoId, token.getLogintokenPK().getAccountid(), seconds);
    }

    @Override
    @Transactional
    public int getInformation(int loginToken, int orderId, int videoId) {
        try {
            if (!isActivated(loginToken)){
                return -1;
            }
        } catch (AccountNotActivatedException ex) {
            return -1;
        }
        LoginToken token = this.videoAccessDao.getLoginToken(loginToken);
        return this.videoPlaybackDao.getVideoPlaybackInformation(orderId, videoId, token.getLogintokenPK().getAccountid());
    }

    @Override
    @Transactional
    public String getVideoFileInformation(int loginToken, int videoId) {
        try {
            if (!isActivated(loginToken)){
                return "";
            }
        } catch (AccountNotActivatedException ex) {
            return "";
        }
        return this.videoPlaybackDao.getFileInformation(videoId);
    }
    
}
