/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samual
 */
@Entity
@Table(name = "video_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VideoInfo.findAll", query = "SELECT v FROM VideoInfo v"),
    @NamedQuery(name = "VideoInfo.findById", query = "SELECT v FROM VideoInfo v WHERE v.id = :id"),
    @NamedQuery(name = "VideoInfo.findByTitle", query = "SELECT v FROM VideoInfo v WHERE v.title = :title"),
    @NamedQuery(name = "VideoInfo.findByPurchasePrice", query = "SELECT v FROM VideoInfo v WHERE v.purchasePrice = :purchasePrice"),
    @NamedQuery(name = "VideoInfo.findByRentalPrice", query = "SELECT v FROM VideoInfo v WHERE v.rentalPrice = :rentalPrice"),
    @NamedQuery(name = "VideoInfo.findByRunningTime", query = "SELECT v FROM VideoInfo v WHERE v.runningTime = :runningTime"),
    @NamedQuery(name = "VideoInfo.findByReleaseDate", query = "SELECT v FROM VideoInfo v WHERE v.releaseDate = :releaseDate")})
public class VideoInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "purchasePrice")
    private int purchasePrice;
    @Basic(optional = false)
    @Column(name = "rentalPrice")
    private int rentalPrice;
    @Basic(optional = false)
    @Column(name = "runningTime")
    private int runningTime;
    @Basic(optional = false)
    @Column(name = "releaseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;
    @JoinColumn(name = "Genre_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Genre genreid;
    @JoinColumn(name = "ScreenRating_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ScreenRating screenRatingid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videoInfo")
    private Collection<Purchase> purchaseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videoInfo")
    private Collection<Rental> rentalCollection;

    public VideoInfo() {
    }

    public VideoInfo(Integer id) {
        this.id = id;
    }

    public VideoInfo(Integer id, String title, String description, int purchasePrice, int rentalPrice, int runningTime, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenreid() {
        return genreid;
    }

    public void setGenreid(Genre genreid) {
        this.genreid = genreid;
    }

    public ScreenRating getScreenRatingid() {
        return screenRatingid;
    }

    public void setScreenRatingid(ScreenRating screenRatingid) {
        this.screenRatingid = screenRatingid;
    }

    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    @XmlTransient
    public Collection<Rental> getRentalCollection() {
        return rentalCollection;
    }

    public void setRentalCollection(Collection<Rental> rentalCollection) {
        this.rentalCollection = rentalCollection;
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
        return "javaapplication5.VideoInfo[ id=" + id + " ]";
    }
    
}
