package com.team33.entities.dao;

import com.team33.entities.Genre;
import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import java.util.List;

/**
 * Dictates the data access methods for browsing the inventory
 *
 * @author Samual
 */
public interface BrowseDao {

    /**
     * Displays all video content in the system
     *
     * @return List<VideoInfo>
     */
    public List<VideoInfo> displayAllVideoContent();

    /**
     * Display a single video info's detail
     *
     * @param videoInfoId
     * @return VideoInfo
     */
    public VideoInfo displayVideoDetails(int videoInfoId);

    /**
     * Searches inventory by genre
     *
     * @param genre @returnList<VideoInfo>
     */
    public List<VideoInfo> searchVideos(Genre genre);

    /**
     * Searches inventory by rating
     *
     * @param rating
     * @return List<VideoInfo>
     */
    public List<VideoInfo> searchVideos(ScreenRating rating);

    /**
     * Searches inventory by title
     *
     * @param title
     * @return List<VideoInfo>
     */
    public List<VideoInfo> searchVideos(String title);
    
    public List<VideoInfo> searchLikeVideos(String title);
}
