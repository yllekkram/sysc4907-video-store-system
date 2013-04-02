/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.playback;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Caleb
 */
@Entity
@Table(name = "VideoFile")
@NamedQueries({
    @NamedQuery(name = "VideoFile.findById", query = "SELECT v FROM VideoFile v WHERE v.videoId = :videoId")
})
public class VideoFile implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "videoId")
    private int videoId;

    @Basic(optional = false)
    @Column(name = "fileLocation")
    private String fileLocation;
    
    public void setFileLocation(String location){
        this.fileLocation = location;
    }
    
    public String getFileLocation(){
        return this.fileLocation;
    }
    
    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int id) {
        this.videoId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += videoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VideoFile)) {
            return false;
        }
        VideoFile other = (VideoFile) object;
        if (this.videoId != other.videoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.playback.VideoFile[ id=" + videoId + " ]";
    }
    
}
