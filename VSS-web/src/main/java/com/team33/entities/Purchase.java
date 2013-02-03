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
@Table(name = "Purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findByAccountId", query = "SELECT p FROM Purchase p WHERE p.Account_id = :Account_id"),
    @NamedQuery(name = "Purchase.findByOrderId", query = "SELECT p FROM Purchase p WHERE p.Order_id = :Order_Id")
})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private PurchasePK purchasePK;
    private VideoInfo videoInfo;

    public Purchase() {
    }

    public Purchase(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    public Purchase(int accountId, int orderId, int videoInfoId) {
        this.purchasePK = new PurchasePK(accountId, orderId, videoInfoId);
    }

    public Purchase(int id, int accountId, int orderId, int videoInfoId) {
        this.purchasePK = new PurchasePK(id, accountId, orderId, videoInfoId);
    }

    @JoinColumns({
        @JoinColumn(name = "Orders_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Orders_Account_id", referencedColumnName = "Account_id", insertable = false, updatable = false),
        @JoinColumn(name = "Video_Info_id", referencedColumnName = "id", insertable = false, updatable = false)
    })
    public PurchasePK getPurchasePK() {
        return this.purchasePK;
    }

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public void setId(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchasePK != null ? purchasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.purchasePK == null && other.purchasePK != null) || (this.purchasePK != null && !this.purchasePK.equals(other.purchasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.Purchase[ purchasePK=" + purchasePK + " ]";
    }
}
