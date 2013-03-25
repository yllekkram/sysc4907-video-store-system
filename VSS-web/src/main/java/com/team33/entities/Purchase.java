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
 * Represents the purchase in the database
 *
 * @author Samual
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
    /**
     *
     */
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

    /**
     * Constructs the purchase
     */
    public Purchase() {
    }

    /**
     * Constructs the purchase
     *
     * @param purchasePK
     */
    public Purchase(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    /**
     * Constructs the purchase
     *
     * @param id
     * @param ordersid
     * @param ordersAccountid
     * @param videoInfoid
     */
    public Purchase(int id, int ordersid, int ordersAccountid, int videoInfoid) {
        this.purchasePK = new PurchasePK(id, ordersid, ordersAccountid, videoInfoid);
    }

    /**
     * Gets the purchase PK
     *
     * @return
     */
    public PurchasePK getPurchasePK() {
        return purchasePK;
    }

    /**
     * Sets the purchase PK
     *
     * @param purchasePK
     */
    public void setPurchasePK(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    /**
     * Gets the video info
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
     * Gets the orders
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
     * hashes the purchase
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchasePK != null ? purchasePK.hashCode() : 0);
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
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.purchasePK == null && other.purchasePK != null) || (this.purchasePK != null && !this.purchasePK.equals(other.purchasePK))) {
            return false;
        }
        return true;
    }

    /**
     * Represents the purchase as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.Purchase[ purchasePK=" + purchasePK + " ]";
    }
}
