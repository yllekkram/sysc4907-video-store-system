package com.team33.services;

import com.team33.entities.Genre;
import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import com.team33.entities.dao.BrowseDaoImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides the functionality for video browsing services
 *
 * @author Samual
 */
public class BrowseServiceImpl implements BrowseService {

    /*
     * Main display of featured content before user filters a new search
     */
    @Autowired
    private BrowseDaoImpl browseDaoImpl;

    /**
     * Sets the current implemented browse dao
     *
     * @param browseDaoImpl
     */
    public void setBrowseDaoImpl(BrowseDaoImpl browseDaoImpl) {
        this.browseDaoImpl = browseDaoImpl;
    }

    /**
     * Displays all video info to the user
     *
     * @return List<VideoInfo>
     */
    @Override
    public List<VideoInfo> displayAllVideoContent() {
        return this.browseDaoImpl.displayAllVideoContent();
    }

    /**
     * Provided the videoInfoId is given displays video info details relevant to
     * the videoInfoId provided
     *
     * @param videoInfoId
     * @return VideoInfo
     */
    @Override
    public VideoInfo displayVideoDetails(int videoInfoId) {
        return this.browseDaoImpl.displayVideoDetails(videoInfoId);
    }

    /**
     * Returns video info for all videos with a specific genre
     *
     * @param genre
     * @return List<VideoInfo>
     */
    @Override
    public List<VideoInfo> searchVideos(Genre genre) {
        return this.browseDaoImpl.searchVideos(genre);
    }

    /**
     * Returns a list of all video info with a particular rating
     *
     * @param rating
     * @return List<VideoInfo>
     */
    @Override
    public List<VideoInfo> searchVideos(ScreenRating rating) {
        return this.browseDaoImpl.searchVideos(rating);
    }

    /**
     * Finds all vide info with a given title
     *
     * @param title
     * @return List<VideoInfo>
     */
    @Override
    public List<VideoInfo> searchVideos(String title) {
        return this.browseDaoImpl.searchVideos(title);
    }
}
