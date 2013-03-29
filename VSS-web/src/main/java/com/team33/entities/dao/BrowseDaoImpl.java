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
 * Provides the dao for the browse inventory service
 *
 * @author Samual
 */
public class BrowseDaoImpl extends HibernateDaoSupport implements BrowseDao {

    //tells Spring to inject the dependency
    @Autowired
    private SessionFactory sessionFactory;
    private static final int FIRST = 0;

    /**
     * Returns a list of videos based on rating
     *
     * @param rating
     * @return List<VideoInfo>
     */
    @Override
    public List<VideoInfo> searchVideos(ScreenRating rating) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findByScreenRating");
        videoQuery.setParameter("Screen_Rating", rating);
        return videoQuery.list();
    }

    /**
     * Returns a list of videos based on title
     *
     * @param title
     * @return List<VideoInfo>
     */
    @Override
    public List<VideoInfo> searchVideos(String title) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findByTitle");
        videoQuery.setParameter("title", title);
        return videoQuery.list();
    }

    /**
     * Returns all videos in a list
     *
     * @return List<VideoInfo>
     */
    @Override
    public List<VideoInfo> displayAllVideoContent() {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findAll");
        return videoQuery.list();
    }

    /**
     * Retrieves details for a given video from its id
     *
     * @param videoInfoId
     * @return VideoInfo
     */
    @Override
    public VideoInfo displayVideoDetails(int videoInfoId) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findById");
        videoQuery.setParameter("id", videoInfoId);
        return (VideoInfo) videoQuery.list().get(FIRST);
    }

    /**
     * Returns a list of videos based on genre
     *
     * @param genre
     * @return
     */
    @Override
    public List<VideoInfo> searchVideos(Genre genre) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findByGenre");
        videoQuery.setParameter("genre", genre);
        return videoQuery.list();
    }
}
