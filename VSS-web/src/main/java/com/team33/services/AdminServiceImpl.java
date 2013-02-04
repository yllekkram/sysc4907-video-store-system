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
public class AdminServiceImpl implements AdminService{
    
    @Autowired
    private AdminDaoImpl adminDaoImpl;
    
    public void setAdminDaoImpl(AdminDaoImpl adminDaoImpl){
        this.adminDaoImpl = adminDaoImpl;
    }

    @Override
    public void addVideoInfo(VideoInfo videoInfo) throws DataAccessException {
       this.addVideoInfo(videoInfo);
    }

    @Override
    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException {
       return this.adminDaoImpl.getVideoInfo(videoInfoId);
    }

    @Override
    public void removeVideoInfo(int videoInfoId) throws DataAccessException {
        this.adminDaoImpl.removeVideoInfo(videoInfoId);
    }
    
}
