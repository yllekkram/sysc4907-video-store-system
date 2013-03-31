/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao.playback;

/**
 *
 * @author Caleb
 */
public interface VideoPlaybackDao {
    
    public String getFileInformation(int videoId);
    
    public int getVideoPlaybackInformation(int orderId, int videoId, int accountId);
    
    public void saveVideoPlaybackInformation(int orderId, int videoId, int accountId, int seconds);
}
