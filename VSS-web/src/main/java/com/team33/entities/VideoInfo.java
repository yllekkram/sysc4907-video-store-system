/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Caleb
 */
@Entity
@Table(name = "Video_Info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VideoInfo.findAll", query = "SELECT v FROM VideoInfo v")
})
public class VideoInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    @Basic(optional = false)
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "durationInSeconds")
    private int durationInSeconds;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private int Screen_Rating_id;
    
    public VideoInfo(){
    }
    
    public VideoInfo(int id){
        this.id = id;
    }
    
    public VideoInfo(int id, String title){
        this(id);
        this.title = title;
    }

    public VideoInfo(int id, String title, String description){
        this(id, title);
        this.description = description;
    }
    
    public VideoInfo(int id, String title, String description, int duration){
        this(id, title, description);
        this.durationInSeconds = duration;
    }
    public int getScreen_Rating_id() {
        return Screen_Rating_id;
    }

    public void setScreen_Rating_id(int Screen_Rating_id) {
        this.Screen_Rating_id = Screen_Rating_id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VideoInfo)) {
            return false;
        }
        VideoInfo other = (VideoInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.VideoInfo[ id=" + id + " ]";
    }
    
}
