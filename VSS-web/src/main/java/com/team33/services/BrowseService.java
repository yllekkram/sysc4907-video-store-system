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
 *
 * @author Samual
 */
public interface BrowseService {

    /**
     *
     * @return
     */
    public List<VideoInfo> displayAllVideoContent();

    /**
     *
     * @param videoInfoId
     * @return
     */
    public VideoInfo displayVideoDetails(int videoInfoId);

    /**
     *
     * @param genre
     * @return
     */
    public List<VideoInfo> searchVideos(Genre genre);

    /**
     *
     * @param rating
     * @return
     */
    public List<VideoInfo> searchVideos(ScreenRating rating);

    /**
     *
     * @param title
     * @return
     */
    public List<VideoInfo> searchVideos(String title);
}
