/*This class will implement methods for the system admin features
 */
package com.team33.services;

import com.team33.entities.VideoInfo;
import com.team33.entities.dao.AdminDaoImpl;
import com.team33.services.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samual
 */
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDaoImpl adminDaoImpl;

    public void setAdminDaoImpl(AdminDaoImpl adminDaoImpl) {
        this.adminDaoImpl = adminDaoImpl;
    }

    public AdminDaoImpl getAdminDaoImpl() {
        return this.adminDaoImpl;
    }

    /*
     * Admin adds only title information for the videoInfo
     */
    @Override
    public void addVideoInfo(String title) {
        this.getAdminDaoImpl().addVideoInfo(title);
    }

    /*
     * Admin only adds the genre and screen rating info for a video
     */
    @Override
    public void addVideoInfo(int genreId, int screenRatingId) {
        this.getAdminDaoImpl().addVideoInfo(genreId, screenRatingId);
    }

    /*
     * Admin adds a video with all the info generated for it
     */
    @Override
    public void addVideoInfo(String title, String description, int rentalPrice, int purchasePrice, int genreId, int screenRatingId) throws DataAccessException {
        this.getAdminDaoImpl().addVideoInfo(title, description, rentalPrice, purchasePrice, genreId, screenRatingId);
    }

    /*
     * Admin updates videos in the system with new information
     */
    @Override
    public void updateVideoDetail(int videoInfoId, String title, String description) {
        this.getAdminDaoImpl().updateVideoDetail(videoInfoId, title, description);
    }

    /*
     * Updates a video with a new description
     */
    @Override
    public void updateVideoDetail(int videoInfoId, String description) {
        this.getAdminDaoImpl().updateVideoDetail(videoInfoId, description);
    }

    /*Updates the video with a genre category e.g. Comedy and a screenrating raitng e.g. PG-13*/
     @Override
    public void updateVideoDetail(int videoInfoId, int genreId, String category, int screenRatingId, String rating) {
        this.getAdminDaoImpl().updateVideoDetail(videoInfoId, genreId, category, screenRatingId, rating);
    }
     
    /*
     * Updates a video with a genre and screen rating
     */
    @Override
    public void updateVideoInfo(int videoInfoId, int genreId, int screenRatingId) {
        this.getAdminDaoImpl().updateVideoInfo(videoInfoId, genreId, screenRatingId);
    }

    /*
     * Updates the video pricing on a particular video
     */
    @Override
    public void updateVideoPricing(int videoInfoId, int purchasePrice, int rentalPrice) {
        this.getAdminDaoImpl().updateVideoPricing(videoInfoId, purchasePrice, rentalPrice);
    }


    /*
     * Get the videoinfo for a particular video
     */
    @Override
    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException {
        return this.adminDaoImpl.getVideoInfo(videoInfoId);
    }

    /*
     * Removes video info from the database
     */
    @Override
    public void removeVideoInfo(int videoInfoId) throws DataAccessException {
        this.adminDaoImpl.removeVideoInfo(videoInfoId);
    }
}
