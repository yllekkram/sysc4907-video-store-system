package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.entities.dao.VideoAccessDao;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class will implement methods to access the videos ordered by an account
 *
 * @author Samual
 */
@Service
public class VideoAccessServiceImpl implements VideoAccessService {

    @Autowired
    private VideoAccessDao videoAccessDao;

    /**
     * Gets the current instance of the implemented dao
     *
     * @return VideoAccessDaoImpl
     */
    public VideoAccessDao getVideoAccessDao() {
        return videoAccessDao;
    }

    /**
     * Sets the current instance of the implemented dao
     *
     * @param videoAccessDaoImpl
     */
    public void setVideoAccessDao(VideoAccessDao videoAccessDao) {
        this.videoAccessDao = videoAccessDao;
    }

    /**
     * Takes the unique id of a login token to determine whether an account is
     * active
     *
     * @param uuid
     * @return boolean
     * @throws AccountNotActivatedException
     */
    public boolean isActivated(int uuid) throws AccountNotActivatedException {
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

    /**
     * Gets the video info for a given a specific videoInfoId and a login token
     * id
     *
     * @param videoInfoId
     * @param uuid
     * @return VideoInfo
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    @Transactional
    public VideoInfo getVideoInfo(int videoInfoId, int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.videoAccessDao.getVideoInfo(videoInfoId, uuid);
        }
        throw new DataAccessException("Incorrect activation key!");
    }

    /**
     * Retrieves a list of vide info given a login token uuid
     *
     * @param uuid
     * @return List<VideoInfo>
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    @Transactional
    public List<VideoInfo> getVideoInfoList(int uuid) throws DataAccessException, AccountNotActivatedException {
        if (this.isActivated(uuid)) {
            return this.videoAccessDao.getVideoInfoList(uuid);
        }
        throw new DataAccessException("Invalid activation key!");
    }
}
