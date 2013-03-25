package com.team33.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents the rentals in the database
 *
 * @author Samual
 */
@Entity
@Table(name = "rental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rental.findAll", query = "SELECT r FROM Rental r"),
    @NamedQuery(name = "Rental.findById", query = "SELECT r FROM Rental r WHERE r.rentalPK.id = :id"),
    @NamedQuery(name = "Rental.findByVideoInfoid", query = "SELECT r FROM Rental r WHERE r.rentalPK.videoInfoid = :videoInfoid"),
    @NamedQuery(name = "Rental.findByOrdersid", query = "SELECT r FROM Rental r WHERE r.rentalPK.ordersid = :ordersid"),
    @NamedQuery(name = "Rental.findByOrdersAccountid", query = "SELECT r FROM Rental r WHERE r.rentalPK.ordersAccountid = :ordersAccountid"),
    @NamedQuery(name = "Rental.findByRentalExpiryDate", query = "SELECT r FROM Rental r WHERE r.rentalPK.rentalExpiryDate = :rentalExpiryDate")})
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @EmbeddedId
    protected RentalPK rentalPK;
    @JoinColumns({
        @JoinColumn(name = "Orders_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Orders_Account_id", referencedColumnName = "Account_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Orders orders;
    @JoinColumn(name = "Video_Info_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VideoInfo videoInfo;

    /**
     * Constructs the rental
     */
    public Rental() {
    }

    /**
     * Constructs the rental
     *
     * @param rentalPK
     */
    public Rental(RentalPK rentalPK) {
        this.rentalPK = rentalPK;
    }

    /**
     * Constructs the rental
     *
     * @param id
     * @param videoInfoid
     * @param ordersid
     * @param ordersAccountid
     * @param rentalExpiryDate
     */
    public Rental(int id, int videoInfoid, int ordersid, int ordersAccountid, Date rentalExpiryDate) {
        this.rentalPK = new RentalPK(id, videoInfoid, ordersid, ordersAccountid, rentalExpiryDate);
    }

    /**
     * Gets the rental PK
     *
     * @return
     */
    public RentalPK getRentalPK() {
        return rentalPK;
    }

    /**
     * Sets the rental PK
     *
     * @param rentalPK
     */
    public void setRentalPK(RentalPK rentalPK) {
        this.rentalPK = rentalPK;
    }

    /**
     * Retrieves the orders
     *
     * @return
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * Sets the orders
     *
     * @param orders
     */
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    /**
     * Retrieves the video info
     *
     * @return
     */
    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    /**
     * Sets the video info
     *
     * @param videoInfo
     */
    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    /**
     * hashes the rental
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentalPK != null ? rentalPK.hashCode() : 0);
        return hash;
    }

    /**
     * Returns true if the objects are equal
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rental)) {
            return false;
        }
        Rental other = (Rental) object;
        if ((this.rentalPK == null && other.rentalPK != null) || (this.rentalPK != null && !this.rentalPK.equals(other.rentalPK))) {
            return false;
        }
        return true;
    }

    /**
     * Represents the rental as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.Rental[ rentalPK=" + rentalPK + " ]";
    }
}
