/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import java.util.List;
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
    public List<VideoInfo> displayFeaturedVideo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<VideoInfo> searchVideos(ScreenRating rating) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<VideoInfo> searchVideos(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
