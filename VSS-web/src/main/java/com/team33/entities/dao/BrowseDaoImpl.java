/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.Genre;
import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Samual
 */
public class BrowseDaoImpl extends HibernateDaoSupport implements BrowseDao {

    //tells Spring to inject the dependency
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    /*Returns a list of videos based on rating*/
    public List<VideoInfo> searchVideos(ScreenRating rating) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findByScreenRating");
        videoQuery.setParameter("Screen_Rating", rating);
        return videoQuery.list();
    }

    @Override
    /*Returns a list of videos based on title*/
    public List<VideoInfo> searchVideos(String title) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findByTitle");
        videoQuery.setParameter("title", title);
        return videoQuery.list();
    }

    @Override
    /*Returns all  videos in a list*/
    public List<VideoInfo> displayAllVideoContent() {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findAll");
        return videoQuery.list();
    }

    @Override
    /*Returns a list of videos based on genre*/
    public List<VideoInfo> searchVideos(Genre genre) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findByGenre");
        videoQuery.setParameter("genre", genre);
        return videoQuery.list();
    }
}
