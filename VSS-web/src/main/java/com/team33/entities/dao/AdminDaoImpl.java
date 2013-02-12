/*
 * This class will implement methods  responsible for the system admin's data access
 */
package com.team33.entities.dao;

import com.team33.entities.*;
import java.util.*;
import com.team33.services.exception.DataAccessException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Samual
 */
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {

    @Autowired
    private SessionFactory sessionFactory;

    //Gets the genre and returns null if genre doesn't exist
    public Genre getGenre(int genreId) {
        if (this.sessionFactory.getCurrentSession().get(Genre.class, genreId) != null) {
            return (Genre) this.sessionFactory.getCurrentSession().get(Genre.class, genreId);
        }
        return null;
    }

    //Gets the screen rating and returns null if ratin doesn't exist
    public ScreenRating getScreenRating(int screenRatingId) {
        if (this.sessionFactory.getCurrentSession().get(ScreenRating.class, screenRatingId) != null) {
            return (ScreenRating) this.sessionFactory.getCurrentSession().get(ScreenRating.class, screenRatingId);
        }
        return null;
    }

    //Gets the video info and retuirns null if a video doesn't exist
    @Override
    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException {
        if (this.sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId) != null) {
            return (VideoInfo) this.sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId);
        }
        return null;
    }

    /*
     * Creates a new video in the system with its title
     */
    @Override
    public void addVideoInfo(String title) {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findAll");
        videoQuery.setParameter("title", title);
        VideoInfo v = new VideoInfo();
        //A video with title already exists
        if (videoQuery.list() != null) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            //newer movie will have current year appended to title e.g. Star Trek 2009
            String newTitle = title + " " + year;
            v.setTitle(newTitle);
            curSession.save(v);
        } else {
            //Title is original to system
            v.setTitle(title);
            curSession.save(v);
        }
    }

    /*
     * Creates a new video in the system with genre and screen rating info
     */
    @Override
    public void addVideoInfo(int genreId, int screenRatingId) {
        VideoInfo v = new VideoInfo();
        if (this.getGenre(genreId) != null) {
            v.setGenre_id(genreId);
        } //Genre id is null
        else {
            if (this.getScreenRating(screenRatingId) != null) {
                //screen rating is valid so save          
                v.setScreenRating_id(screenRatingId);
                this.sessionFactory.getCurrentSession().save(v);
            } //both ids are null
            else {
                Genre g = new Genre();
                ScreenRating r = new ScreenRating();
                v.setGenre_id(g.getId());
                v.setScreenRating_id(r.getId());
            }

        }
    }

    /*
     * Add video info with pricing information and detail information
     */
    @Override
    public void addVideoInfo(String title, String description, int rentalPrice, int purchasePrice, int genreId, int screenRatingId) throws DataAccessException {
        Session curSession = this.getSessionFactory().getCurrentSession();
        Query videoQuery;
        videoQuery = curSession.getNamedQuery("VideoInfo.findAll");
        videoQuery.setParameter("title", title);
        VideoInfo v = new VideoInfo();
        //A video with title already exists
        if (videoQuery.list() != null) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            //newer movie will have current year appended to title e.g. Star Trek 2009
            String newTitle = title + " " + year;
            v.setTitle(newTitle);
            v.setDescription(description);
            v.setRentalPrice(Math.abs(rentalPrice));
            v.setPurchasePrice(Math.abs(purchasePrice));
            if (this.getGenre(genreId) != null) {
                v.setGenre_id(genreId);
            } //Genre id is null
            else {
                if (this.getScreenRating(screenRatingId) != null) {
                    //screen rating is valid so save          
                    v.setScreenRating_id(screenRatingId);
                    curSession.save(v);
                } //both ids are null
                else {
                    Genre g = new Genre();
                    ScreenRating r = new ScreenRating();
                    v.setGenre_id(g.getId());
                    v.setScreenRating_id(r.getId());
                    curSession.save(v);
                }
            }
        }
    }

    /*
     * Updates a video with the title and descrpition details
     */
    @Override
    public void updateVideoDetail(int videoInfoId, String title, String description) {
        if (this.getVideoInfo(videoInfoId) != null) {
            VideoInfo v = (VideoInfo) this.sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId);
            v.setDescription(description);
            v.setTitle(title);
            this.sessionFactory.getCurrentSession().update(v);
        }
    }

    @Override
    public void updateVideoDetail(int videoInfoId, String description) {
        if (this.getVideoInfo(videoInfoId) != null) {
            VideoInfo v = (VideoInfo) this.sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId);
            v.setDescription(description);
            this.sessionFactory.getCurrentSession().update(v);
        }
    }

    /*
     * Update the genre and screen rating details
     */
    @Override
    public void updateVideoDetail(int videoInfoId, int genreId, String category, int screenRatingId, String rating) {
        if (this.getVideoInfo(videoInfoId) != null) {
            VideoInfo v = (VideoInfo) this.sessionFactory.getCurrentSession().get(VideoInfo.class, videoInfoId);
            if (this.getGenre(genreId) != null) {
                v.setGenre_id(genreId);
            } //Genre id is null
            else {
                if (this.getScreenRating(screenRatingId) != null) {
                    //screen rating is valid so save          
                    v.setScreenRating_id(screenRatingId);
                } //both ids are null
                else {
                    Genre g = new Genre();
                    g.setCategory(category);
                    ScreenRating r = new ScreenRating();
                    r.setRatingType(rating);
                    v.setGenre_id(g.getId());
                    v.setScreenRating_id(r.getId());
                }
            };
            this.sessionFactory.getCurrentSession().update(v);
        };
    }

    /*
     * Update video info with initial genre and screen rating
     */
    @Override
    public void updateVideoInfo(int videoInfoId, int genreId, int screenRatingId) {
        if (this.getVideoInfo(videoInfoId) != null) {
            Session curSession = this.sessionFactory.getCurrentSession();
            VideoInfo v = (VideoInfo) curSession.load(VideoInfo.class, videoInfoId);
            if (this.getGenre(genreId) != null) {
                v.setGenre_id(genreId);
            } //Genre id is null
            else {
                if (this.getScreenRating(screenRatingId) != null) {
                    //screen rating is valid so save          
                    v.setScreenRating_id(screenRatingId);
                    curSession.update(v);
                } //both ids are null
                else {
                    Genre g = new Genre();
                    ScreenRating r = new ScreenRating();
                    v.setGenre_id(g.getId());
                    v.setScreenRating_id(r.getId());
                    curSession.update(v);
                }
            }
        }
    }

    /*Updates the video pricing*/
    @Override
    public void updateVideoPricing(int videoInfoId, int purchasePrice, int rentalPrice) {
        if (this.getVideoInfo(videoInfoId) != null) {
            Session curSession = this.sessionFactory.getCurrentSession();
            VideoInfo v = (VideoInfo) curSession.load(VideoInfo.class, videoInfoId);
            v.setRentalPrice(Math.abs(rentalPrice));
            v.setPurchasePrice(Math.abs(purchasePrice));
            curSession.update(v);
        }
    }

    /*Removes video info from the database*/
    @Override
    public void removeVideoInfo(int videoInfoId) throws DataAccessException {
        VideoInfo vi = (VideoInfo) sessionFactory.getCurrentSession().load(
                VideoInfo.class, videoInfoId);
        if (null != vi) {
            sessionFactory.getCurrentSession().delete(vi);
        }
    }
}
