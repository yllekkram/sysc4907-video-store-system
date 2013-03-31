/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.playback;

/**
 *
 * @author Caleb
 */
public interface VideoPlaybackService {
    
    public void saveInformation(int loginToken, int orderId, int videoId, int seconds);
    
    public String getVideoFileInformation(int loginToken, int videoId);
    
    public int getInformation(int loginToken, int orderId, int videoId);
}
