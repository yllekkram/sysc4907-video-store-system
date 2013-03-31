/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.playback;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Caleb
 */
@Entity
@Table(name = "VideoPlayback")
@NamedQueries({
    @NamedQuery(name = "VideoPlayback.findByKey", query = "SELECT v FROM VideoPlayback v WHERE v.playbackPK.orderId = :orderId AND v.playbackPK.videoId = :videoId AND v.playbackPK.accountId = :accountId")
})
public class VideoPlayback implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private VideoPlaybackPK playbackPK;
    
    @Basic(optional = false)
    @Column(name = "currentTime")
    private int currentTime;

    public VideoPlayback(){
    }
    
    public VideoPlayback(int orderId, int accountId, int videoId, int currentTime){
        this.playbackPK = new VideoPlaybackPK(orderId, videoId, accountId);
        this.currentTime = currentTime;
    }
    
    public VideoPlaybackPK getPlaybackPK() {
        return playbackPK;
    }

    public void setPlaybackPK(VideoPlaybackPK playbackPK) {
        this.playbackPK = playbackPK;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.playbackPK != null ? this.playbackPK.hashCode() : 0);
        hash = 43 * hash + this.currentTime;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VideoPlayback other = (VideoPlayback) obj;
        if (this.playbackPK != other.playbackPK && (this.playbackPK == null || !this.playbackPK.equals(other.playbackPK))) {
            return false;
        }
        if (this.currentTime != other.currentTime) {
            return false;
        }
        return true;
    }
    
}
