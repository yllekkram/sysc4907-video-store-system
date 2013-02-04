/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import com.team33.entities.dao.BrowseDaoImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samual
 */
public class BrowseServiceImpl implements BrowseService {
    
    @Override
    public List<VideoInfo> searchVideos(ScreenRating rating) {
        return this.browseDaoImpl.searchVideos(rating);
    }
    
    @Override
    public List<VideoInfo> searchVideos(String title) {
        return this.browseDaoImpl.searchVideos(title);
    }
    /*
     * Main display of featured content before user filters a new search
     */
    @Autowired
    private BrowseDaoImpl browseDaoImpl;
    
    public void setBrowseDaoImpl(BrowseDaoImpl browseDaoImpl) {
        this.browseDaoImpl = browseDaoImpl;
    }

    @Override
    public List<VideoInfo> displayFeaturedVideo() {
        return this.browseDaoImpl.displayFeaturedVideo();
    }
    
    public void setBrowseDaoImpl(BrowseDaoImpl browseDaoImpl){
    
    }
}
