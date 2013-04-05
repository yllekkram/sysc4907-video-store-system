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
 * Represents the video info in the database
 *
 * @author Samual
 */
@Entity
@Table(name = "video_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VideoInfo.findAll", query = "SELECT v FROM VideoInfo v"),
    @NamedQuery(name = "VideoInfo.findById", query = "SELECT v FROM VideoInfo v WHERE v.id = :id"),
    @NamedQuery(name = "VideoInfo.findByLike", query = "SELECT v FROM VideoInfo v WHERE v.title LIKE :title"),
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

    /**
     *
     */
    public VideoInfo() {
    }

    /**
     * Constructs the video info
     *
     * @param id
     */
    public VideoInfo(Integer id) {
        this.id = id;
    }

    /**
     * Constructs the video info
     *
     * @param id
     * @param title
     * @param description
     * @param purchasePrice
     * @param rentalPrice
     * @param runningTime
     * @param releaseDate
     */
    public VideoInfo(Integer id, String title, String description, int purchasePrice, int rentalPrice, int runningTime, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
        this.runningTime = runningTime;
        this.releaseDate = releaseDate;
    }

    /**
     * Returns the id for video info
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the title of the video
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the description for video
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the purchase price of a video
     *
     * @return
     */
    public int getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Sets the price
     *
     * @param purchasePrice
     */
    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Retrieves the rental price
     *
     * @return
     */
    public int getRentalPrice() {
        return rentalPrice;
    }

    /**
     * Sets the price
     *
     * @param rentalPrice
     */
    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    /**
     * Retrieves the running time
     *
     * @return
     */
    public int getRunningTime() {
        return runningTime;
    }

    /**
     * Sets the running time of the video
     *
     * @param runningTime
     */
    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    /**
     * Retrieves the release date
     *
     * @return
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the release date
     *
     * @param releaseDate
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Retrieves the id for the genre
     *
     * @return
     */
    public Genre getGenreid() {
        return genreid;
    }

    /**
     * sets genre id
     *
     * @param genreid
     */
    public void setGenreid(Genre genreid) {
        this.genreid = genreid;
    }

    /**
     * Retrieves screen rating id
     *
     * @return
     */
    public ScreenRating getScreenRatingid() {
        return screenRatingid;
    }

    /**
     * Sets the screen rating id
     *
     * @param screenRatingid
     */
    public void setScreenRatingid(ScreenRating screenRatingid) {
        this.screenRatingid = screenRatingid;
    }

    /**
     * returns a collection of purchases
     *
     * @return
     */
    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    /**
     * Sets the purchase collection
     *
     * @param purchaseCollection
     */
    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    /**
     * Retrieves the collection of rentals
     *
     * @return
     */
    @XmlTransient
    public Collection<Rental> getRentalCollection() {
        return rentalCollection;
    }

    /**
     * Sets the collection of rentals
     *
     * @param rentalCollection
     */
    public void setRentalCollection(Collection<Rental> rentalCollection) {
        this.rentalCollection = rentalCollection;
    }

    /**
     * hashes the video info
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Returns true if objects are equal
     *
     * @param object
     * @return
     */
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

    /**
     * Represents the video info as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.VideoInfo[ id=" + id + " ]";
    }
}
