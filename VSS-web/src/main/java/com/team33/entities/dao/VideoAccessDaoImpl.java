/*
 * Data Access layer for the video access features
 */
package com.team33.entities.dao;

import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Mark,Samual
 */
public class VideoAccessDaoImpl extends HibernateDaoSupport implements
        VideoAccessDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param videoInfoId
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public VideoInfo getVideoInfo(int videoInfoId,int uuid) throws DataAccessException,AccountNotActivatedException {
        return (VideoInfo) this.sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId);
    }

    /**
     *
     * @param uuid
     * @return
     * @throws DataAccessException
     * @throws AccountNotActivatedException
     */
    @Override
    public List<VideoInfo> getVideoInfoList(int uuid) throws DataAccessException,AccountNotActivatedException{
        return sessionFactory.getCurrentSession().getNamedQuery("VideoInfo.findAll").list();
    }
     /**
     *
     * @param uuid
     * @return
     * @throws DataAccessException
     */
    @Override
    public LoginToken getLoginToken(int uuid) throws DataAccessException {
         if(sessionFactory.getCurrentSession().get(LoginToken.class,uuid) != null){
             return (LoginToken)sessionFactory.getCurrentSession().get(LoginToken.class,uuid);
         }
        throw new DataAccessException("The activation key is invalid");
    }
}
