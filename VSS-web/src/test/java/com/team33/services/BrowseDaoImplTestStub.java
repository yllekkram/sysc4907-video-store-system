/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Genre;
import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import com.team33.entities.dao.BrowseDaoImpl;
import com.team33.services.exception.DataAccessException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caleb
 */
public class BrowseDaoImplTestStub extends BrowseDaoImpl{
    
    private ArrayList<VideoInfo> dummyVideoList;
    
    public BrowseDaoImplTestStub(){
        this.dummyVideoList = new ArrayList<VideoInfo>();
    }
    
    @Override
    public List<VideoInfo> searchVideos(ScreenRating rating){
        if (rating == null){
            throw new DataAccessException("Dummy Message");
        }else if(rating.getId().intValue() == 9999){
            return new ArrayList<VideoInfo>();
        }else if(rating.getId().intValue() == 0){
            return this.dummyVideoList;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public List<VideoInfo> searchVideos(String title){
        if (title == null){
            throw new DataAccessException("Dummy Message");
        }else if (title.equals("")){
            return new ArrayList<VideoInfo>();
        }else if (title.equals("Not Going to Find Something")){
            return new ArrayList<VideoInfo>();
        }else if (title.equals("Single Title")){
            return this.dummyVideoList;
        }else if (title.equals("Multiple Title")){
        
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public List<VideoInfo> searchVideos(Genre genre){
        if (genre == null){
            throw new DataAccessException("Should not reach here");
        }else if (genre.getId().intValue() == -1){
            throw new DataAccessException("Should not reach here");
        }else if (genre.getId().intValue() == 9999){
            throw new DataAccessException("Should not reach here");
        }else if (genre.getId().intValue() == 0){
            return this.dummyVideoList;
        }
        throw new DataAccessException("Should not reach here");
    }
    
    @Override
    public List<VideoInfo> displayAllVideoContent(){
        return this.dummyVideoList;
    }
    
    @Override
    public VideoInfo displayVideoDetails(int videoInfoId){
        if (videoInfoId == -1){
            throw new DataAccessException("Should not reach here");
        }else if (videoInfoId == 9999){
            throw new DataAccessException("Dummy Message");
        }else if (videoInfoId == 0){
            return null;
        }
        throw new DataAccessException("Should not reach here");
    }
}
