/*
 * This interface will establish methods  responsible for the system admin's data access
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
import com.team33.services.exception.DataAccessException;

/**
 *
 * @author Samual
 */
public interface AdminDao {

    public void addVideoInfo(String title);

    public void addVideoInfo(int genreId, int screenRatingId);

    public void addVideoInfo(String title, String description, int rentalPrice, int purchasePrice, int genreId, int screenRatingId) throws DataAccessException;

    public void updateVideoInfo(int videoInfoId, int genreId, int screenRatingId);

    public void updateVideoDetail(int videoInfoId, String title, String description);

    public void updateVideoDetail(int videoInfoId, String description);

    public void updateVideoDetail(int videoInfoId, int genreId, String category, int screenRatingId, String rating);

    public void updateVideoPricing(int videoInfoId, int purchasePrice, int rentalPrice);

    public void removeVideoInfo(int videoInfoId) throws DataAccessException;

    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException;
}
