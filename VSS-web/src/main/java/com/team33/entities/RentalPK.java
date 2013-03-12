package com.team33.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents the primary key for rentals
 *
 * @author Samual
 */
@Embeddable
public class RentalPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Video_Info_id")
    private int videoInfoid;
    @Basic(optional = false)
    @Column(name = "Orders_id")
    private int ordersid;
    @Basic(optional = false)
    @Column(name = "Orders_Account_id")
    private int ordersAccountid;
    @Basic(optional = false)
    @Column(name = "rentalExpiryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentalExpiryDate;

    /**
     * Constructs the primary key for rental
     */
    public RentalPK() {
    }

    /**
     * Constructs the primary key for rental
     *
     * @param id
     * @param videoInfoid
     * @param ordersid
     * @param ordersAccountid
     * @param rentalExpiryDate
     */
    public RentalPK(int id, int videoInfoid, int ordersid, int ordersAccountid, Date rentalExpiryDate) {
        this.id = id;
        this.videoInfoid = videoInfoid;
        this.ordersid = ordersid;
        this.ordersAccountid = ordersAccountid;
        this.rentalExpiryDate = rentalExpiryDate;
    }

    /**
     * Retrieves the id for the primary key
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the video info id
     *
     * @return
     */
    public int getVideoInfoid() {
        return videoInfoid;
    }

    /**
     * Sets video info id
     *
     * @param videoInfoid
     */
    public void setVideoInfoid(int videoInfoid) {
        this.videoInfoid = videoInfoid;
    }

    /**
     * Retrieves the order id
     *
     * @return
     */
    public int getOrdersid() {
        return ordersid;
    }

    /**
     * Sets order id
     *
     * @param ordersid
     */
    public void setOrdersid(int ordersid) {
        this.ordersid = ordersid;
    }

    /**
     * Retrieves the order account id
     *
     * @return
     */
    public int getOrdersAccountid() {
        return ordersAccountid;
    }

    /**
     * Sets the order account id
     *
     * @param ordersAccountid
     */
    public void setOrdersAccountid(int ordersAccountid) {
        this.ordersAccountid = ordersAccountid;
    }

    /**
     * Retrieves the expiry date for a rental
     *
     * @return
     */
    public Date getRentalExpiryDate() {
        return rentalExpiryDate;
    }

    /**
     * Sets the expiry date
     *
     * @param rentalExpiryDate
     */
    public void setRentalExpiryDate(Date rentalExpiryDate) {
        this.rentalExpiryDate = rentalExpiryDate;
    }

    /**
     * hashes the rental PK
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) videoInfoid;
        hash += (int) ordersid;
        hash += (int) ordersAccountid;
        hash += (rentalExpiryDate != null ? rentalExpiryDate.hashCode() : 0);
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
        if (!(object instanceof RentalPK)) {
            return false;
        }
        RentalPK other = (RentalPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.videoInfoid != other.videoInfoid) {
            return false;
        }
        if (this.ordersid != other.ordersid) {
            return false;
        }
        if (this.ordersAccountid != other.ordersAccountid) {
            return false;
        }
        if ((this.rentalExpiryDate == null && other.rentalExpiryDate != null) || (this.rentalExpiryDate != null && !this.rentalExpiryDate.equals(other.rentalExpiryDate))) {
            return false;
        }
        return true;
    }

    /**
     * Represents the rental PK a a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.RentalPK[ id=" + id + ", videoInfoid=" + videoInfoid + ", ordersid=" + ordersid + ", ordersAccountid=" + ordersAccountid + ", rentalExpiryDate=" + rentalExpiryDate + " ]";
    }
}
