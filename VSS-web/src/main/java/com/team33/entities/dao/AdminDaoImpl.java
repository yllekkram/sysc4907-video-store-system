/*
 * This class will implement methods  responsible for the system admin's data access
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
import com.team33.services.exception.DataAccessException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Samual
 */
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addVideoInfo(VideoInfo videoInfo) throws DataAccessException {
       this.sessionFactory.getCurrentSession().save(videoInfo);
    }

    @Override
    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException {
        return (VideoInfo)this.sessionFactory.getCurrentSession().load(VideoInfo.class, videoInfoId);
    }

    @Override
    public void removeVideoInfo(int videoInfoId) throws DataAccessException {
        VideoInfo vi = (VideoInfo) sessionFactory.getCurrentSession().load(
                VideoInfo.class, videoInfoId);
        if (null != vi) {
            sessionFactory.getCurrentSession().delete(vi);
        }
    }
    
}
