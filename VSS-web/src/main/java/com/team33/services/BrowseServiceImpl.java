/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Genre;
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

    /*
     * Main display of featured content before user filters a new search
     */
    @Autowired
    private BrowseDaoImpl browseDaoImpl;

    public void setBrowseDaoImpl(BrowseDaoImpl browseDaoImpl) {
        this.browseDaoImpl = browseDaoImpl;
    }

    @Override
    public List<VideoInfo> displayAllVideoContent() {
        return this.browseDaoImpl.displayAllVideoContent();
    }

    @Override
    public VideoInfo displayVideoDetails(int videoInfoId) {
        return this.browseDaoImpl.displayVideoDetails(videoInfoId);
    }

    @Override
    public List<VideoInfo> searchVideos(Genre genre) {
        return this.browseDaoImpl.searchVideos(genre);
    }

    @Override
    public List<VideoInfo> searchVideos(ScreenRating rating) {
        return this.browseDaoImpl.searchVideos(rating);
    }

    @Override
    public List<VideoInfo> searchVideos(String title) {
        return this.browseDaoImpl.searchVideos(title);
    }
}
