/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.playback;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Caleb
 */
@Embeddable
public class VideoPlaybackPK implements Serializable{
    
    @Basic(optional = false)
    @Column(name = "orderId")
    private int orderId;
    
    @Basic(optional = false)
    @Column(name = "videoId")
    private int videoId;
    
    @Basic(optional = false)
    @Column(name = "accountId")
    private int accountId;
    
    public VideoPlaybackPK(){
    }
    
    public VideoPlaybackPK(int orderId, int videoId, int accountId){
        this.orderId = orderId;
        this.videoId = videoId;
        this.accountId = accountId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.orderId;
        hash = 79 * hash + this.videoId;
        hash = 79 * hash + this.accountId;
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
        final VideoPlaybackPK other = (VideoPlaybackPK) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.videoId != other.videoId) {
            return false;
        }
        if (this.accountId != other.accountId) {
            return false;
        }
        return true;
    }
    
}
