/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao.playback;

import com.team33.entities.playback.VideoPlayback;
import com.team33.services.exception.DataAccessException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Caleb
 */
@Repository
public class VideoPlaybackDaoImpl implements VideoPlaybackDao{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public String getFileInformation(int videoId){
        Session curSession = sessionFactory.getCurrentSession();
        if (curSession == null){
            throw new DataAccessException("Invalid session");
        }
        Query query = curSession.getNamedQuery("VideoFile.findById");
        query.setParameter("id", videoId);
        if (query.list().isEmpty()){
            throw new DataAccessException("Video Not Found");
        }
        return (String) query.list().get(0);
    }
    
    @Override
    public int getVideoPlaybackInformation(int orderId, int videoId, int accountId){
        Session curSession = sessionFactory.getCurrentSession();
        if (curSession == null){
            throw new DataAccessException("Invalid session");
        }
        Query query = curSession.getNamedQuery("VideoPlayback.findByKey");
        query.setParameter("orderId", orderId);
        query.setParameter("videoId", videoId);
        query.setParameter("accountId", accountId);
        if (query.list().isEmpty()){
            return -1;
        }
        return ((Integer)query.list().get(0)).intValue();
    }
    
    @Override
    public void saveVideoPlaybackInformation(int orderId, int videoId, int accountId, int seconds){
        VideoPlayback playback = new VideoPlayback(orderId, accountId, videoId, seconds);
        sessionFactory.getCurrentSession().save(playback);
    }
    
}
