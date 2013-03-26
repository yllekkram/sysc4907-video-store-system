/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Genre;
import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import java.util.List;

/**
 * This interface dictate all services required for browsing the system's
 * inventory
 *
 * @author Samual
 */
public interface BrowseService {

    /**
     * Retrieves all video content for the user
     *
     * @return List<VideoInfo>
     */
    public List<VideoInfo> displayAllVideoContent();

    /**
     * Retrieves the details for a single video
     *
     * @param videoInfoId
     * @return VideoInfo
     */
    public VideoInfo displayVideoDetails(int videoInfoId);

    /**
     * Retrieves all video content for a particular genre
     *
     * @param genre
     * @return List<VideoInfo>
     */
    public List<VideoInfo> searchVideos(Genre genre);

    /**
     * Retrieves all video content with a particular rating
     *
     * @param rating
     * @return
     */
    public List<VideoInfo> searchVideos(ScreenRating rating);

    /**
     * Retrieves all video content with a particular title
     *
     * @param title
     * @return
     */
    public List<VideoInfo> searchVideos(String title);
}
