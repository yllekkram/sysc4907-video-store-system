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

    @Override
    public VideoInfo getVideoInfo(int videoInfoId,LoginToken loginToken) throws DataAccessException,AccountNotActivatedException {
        if (!loginToken.getAccount().getActivated()) {
            throw new AccountNotActivatedException("Account Inactive");
        }
        return (VideoInfo) this.sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId);
    }

    @Override
    public List<VideoInfo> getVideoInfoList(LoginToken loginToken) throws DataAccessException,AccountNotActivatedException{
        if (!loginToken.getAccount().getActivated()) {
            throw new AccountNotActivatedException("Account Inactive");
        }
        return sessionFactory.getCurrentSession().getNamedQuery("VideoInfo.findAll").list();
    }
}
