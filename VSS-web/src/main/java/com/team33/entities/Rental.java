/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samual
 */
@Entity
@Table(name = "Rental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rental.findAll", query = "SELECT r FROM Rental r"),
    @NamedQuery(name = "Rental.findByAccountId", query = "SELECT r FROM Rental r WHERE r.Account_id = :Account_id"),
    @NamedQuery(name = "Rental.findByOrderId", query = "SELECT r FROM Rental r WHERE r.Order_id = :Order_Id"),
    @NamedQuery(name = "Rental.findAll", query = "SELECT r FROM Rental r WHERE r.rentalExpiryDate = `rentalExpiryDate")
})
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RentalPK rentalPK;
    private VideoInfo videoInfo;

    public Rental() {
    }

    public Rental(RentalPK rentalPK) {
        this.rentalPK = rentalPK;
    }

    public Rental(int accountId, int orderId, int videoInfoId) {
        this.rentalPK = new RentalPK(accountId, orderId, videoInfoId);
    }

    public Rental(int id, int accountId, int orderId, int videoInfoId) {
        this.rentalPK = new RentalPK(id, accountId, orderId, videoInfoId);
    }

    @JoinColumns({
        @JoinColumn(name = "Orders_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Orders_Account_id", referencedColumnName = "Account_id", insertable = false, updatable = false),
        @JoinColumn(name = "Video_Info_id", referencedColumnName = "id", insertable = false, updatable = false)
    })
    public RentalPK getRentalPK() {
        return this.rentalPK;
    }

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public void setRentalPK(RentalPK rentalPK) {
        this.rentalPK = rentalPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentalPK != null ? rentalPK.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "com.team33.entities.Rental[ rentalK=" + rentalPK + " ]";
    }
}
