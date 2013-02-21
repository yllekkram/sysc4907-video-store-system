/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.VideoInfo;
import com.team33.entities.dao.VideoInfoDaoImpl;
import com.team33.services.exception.DataAccessException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caleb
 */
public class VideoInfoDaoImplTestStub extends VideoInfoDaoImpl{
    
    private ArrayList<VideoInfo> dummyVideoInfoList;
    
    public VideoInfoDaoImplTestStub(){
        this.dummyVideoInfoList = new ArrayList<VideoInfo>();
    }
    
    @Override
    public List<VideoInfo> getVideoInfoList() throws DataAccessException {
        return this.dummyVideoInfoList;
    }
    
    @Override
    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException {
        if (videoInfoId == -1){
            throw new DataAccessException("Dummy Message");
        }else if (videoInfoId == 9999){
            throw new DataAccessException("Dummy Message");
        }else if (videoInfoId == 0){
            return new VideoInfo(0);
        }
        throw new DataAccessException("Should not reach here"); 
    }
    
    @Override
    public void saveVideoInfo(int videoInfoId) throws DataAccessException {
        if (videoInfoId == -1){
            throw new DataAccessException("Dummy Message");
        }else if (videoInfoId == 9999){
            throw new DataAccessException("Dummy Message");
        }else if (videoInfoId == 0){
            return;
        }
        throw new DataAccessException("Should not reach here"); 
    }
    
    @Override
    public void removeVideoInfo(int videoInfoId) throws DataAccessException {
        if (videoInfoId == -1){
            throw new DataAccessException("Dummy Message");
        }else if (videoInfoId == 9999){
            throw new DataAccessException("Dummy Message");
        }else if (videoInfoId == 0){
            return;
        }
        throw new DataAccessException("Should not reach here"); 
    }
}
