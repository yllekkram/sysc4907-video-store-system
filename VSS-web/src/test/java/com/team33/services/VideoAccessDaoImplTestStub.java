/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.entities.dao.VideoAccessDaoImpl;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;

/**
 *
 * @author Caleb
 */
public class VideoAccessDaoImplTestStub extends VideoAccessDaoImpl{
    
    
    @Override
    public VideoInfo getVideoInfo(int videoInfoId,int uuid) throws DataAccessException,AccountNotActivatedException {
        if (videoInfoId == -1){
            throw new DataAccessException("Dummy Message");
        }else if(videoInfoId == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == -1){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 0 && videoInfoId == 0){
            
        }
        throw new DataAccessException("Should not reach here"); 
    }
    
    @Override
    public List<VideoInfo> getVideoInfoList(int uuid) throws DataAccessException,AccountNotActivatedException{
        if(uuid == -1){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 0){
            
        }
        throw new DataAccessException("Should not reach here"); 
    }
    
    @Override
    public LoginToken getLoginToken(int uuid) throws DataAccessException {
        if (uuid == -1){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 9999){
            throw new DataAccessException("Dummy Message");
        }else if(uuid == 0){
            return new LoginToken(0, 0);
        }
        throw new DataAccessException("Should not reach here"); 
    }
}
