/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mark
 */
@Repository
public class VideoInfoDaoImpl implements VideoInfoDao{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<VideoInfo> getVideoInfoList() throws DataAccessException {
        return sessionFactory.getCurrentSession().getNamedQuery("VideoInfo.findAll").list();
    }

    @Override
    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException {
        return (VideoInfo)sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId);
    }

    @Override
    public void saveVideoInfo(int videoInfoId) throws DataAccessException {
        VideoInfo vi = (VideoInfo)sessionFactory.getCurrentSession().get(
                VideoInfo.class, videoInfoId);
        if (vi != null) {
            sessionFactory.getCurrentSession().save(vi);
        }
    }

    @Override
    public void removeVideoInfo(int videoInfoId) throws DataAccessException {
        VideoInfo vi = (VideoInfo)sessionFactory.getCurrentSession().get(
                VideoInfo.class, videoInfoId);
        if (vi != null) {
            sessionFactory.getCurrentSession().save(vi);
        }
    }
}
