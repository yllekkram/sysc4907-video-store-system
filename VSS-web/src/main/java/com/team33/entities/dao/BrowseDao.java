/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.Genre;
import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import java.util.List;

/**
 *
 * @author Samual
 */
public interface BrowseDao {

    //changed a featured content list to dislay all content
    public List<VideoInfo> displayAllVideoContent();

    public List<VideoInfo> searchVideos(Genre genre);

    public List<VideoInfo> searchVideos(ScreenRating rating);

    public List<VideoInfo> searchVideos(String title);
}
