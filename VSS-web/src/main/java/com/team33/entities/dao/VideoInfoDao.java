/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
import java.util.List;
import org.springframework.dao.DataAccessException;
/**
 *
 * @author Caleb
 */
public interface VideoInfoDao {
    
    public List<VideoInfo> getVideoInfoList() throws DataAccessException;
    
    public VideoInfo getVideoInfo(int videoInfoId) throws DataAccessException;
    
    public void saveVideoInfo(int videoInfoId) throws DataAccessException;
    
    public void removeVideoInfo(int videoInfoId) throws DataAccessException;
}
