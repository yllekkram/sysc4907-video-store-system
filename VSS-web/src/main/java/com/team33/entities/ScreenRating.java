/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LaFamiglia
 */
@Entity
@Table(name = "screen_rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScreenRating.findAll", query = "SELECT s FROM ScreenRating s"),
    @NamedQuery(name = "ScreenRating.findById", query = "SELECT s FROM ScreenRating s WHERE s.id = :id"),
    @NamedQuery(name = "ScreenRating.findByRatingType", query = "SELECT s FROM ScreenRating s WHERE s.ratingType = :ratingType")})
public class ScreenRating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ratingType")
    private String ratingType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "screenRatingid")
    private Collection<VideoInfo> videoInfoCollection;

    public ScreenRating() {
    }

    public ScreenRating(Integer id) {
        this.id = id;
    }

    public ScreenRating(Integer id, String ratingType) {
        this.id = id;
        this.ratingType = ratingType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
    }

    @XmlTransient
    public Collection<VideoInfo> getVideoInfoCollection() {
        return videoInfoCollection;
    }

    public void setVideoInfoCollection(Collection<VideoInfo> videoInfoCollection) {
        this.videoInfoCollection = videoInfoCollection;
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
        if (!(object instanceof ScreenRating)) {
            return false;
        }
        ScreenRating other = (ScreenRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.ScreenRating[ id=" + id + " ]";
    }
    
}
