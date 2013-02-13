/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
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
 *
 * @author LaFamiglia
 */
@Entity
@Table(name = "purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findById", query = "SELECT p FROM Purchase p WHERE p.purchasePK.id = :id"),
    @NamedQuery(name = "Purchase.findByOrdersid", query = "SELECT p FROM Purchase p WHERE p.purchasePK.ordersid = :ordersid"),
    @NamedQuery(name = "Purchase.findByOrdersAccountid", query = "SELECT p FROM Purchase p WHERE p.purchasePK.ordersAccountid = :ordersAccountid"),
    @NamedQuery(name = "Purchase.findByVideoInfoid", query = "SELECT p FROM Purchase p WHERE p.purchasePK.videoInfoid = :videoInfoid")})
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PurchasePK purchasePK;
    @JoinColumn(name = "Video_Info_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VideoInfo videoInfo;
    @JoinColumns({
        @JoinColumn(name = "Orders_id", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "Orders_Account_id", referencedColumnName = "Account_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Orders orders;

    public Purchase() {
    }

    public Purchase(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    public Purchase(int id, int ordersid, int ordersAccountid, int videoInfoid) {
        this.purchasePK = new PurchasePK(id, ordersid, ordersAccountid, videoInfoid);
    }

    public PurchasePK getPurchasePK() {
        return purchasePK;
    }

    public void setPurchasePK(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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
        return "javaapplication5.Purchase[ purchasePK=" + purchasePK + " ]";
    }
    
}
