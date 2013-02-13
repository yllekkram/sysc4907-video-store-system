/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Samual
 */
@Embeddable
public class PurchasePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Orders_id")
    private int ordersid;
    @Basic(optional = false)
    @Column(name = "Orders_Account_id")
    private int ordersAccountid;
    @Basic(optional = false)
    @Column(name = "Video_Info_id")
    private int videoInfoid;

    public PurchasePK() {
    }

    public PurchasePK(int id, int ordersid, int ordersAccountid, int videoInfoid) {
        this.id = id;
        this.ordersid = ordersid;
        this.ordersAccountid = ordersAccountid;
        this.videoInfoid = videoInfoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(int ordersid) {
        this.ordersid = ordersid;
    }

    public int getOrdersAccountid() {
        return ordersAccountid;
    }

    public void setOrdersAccountid(int ordersAccountid) {
        this.ordersAccountid = ordersAccountid;
    }

    public int getVideoInfoid() {
        return videoInfoid;
    }

    public void setVideoInfoid(int videoInfoid) {
        this.videoInfoid = videoInfoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) ordersid;
        hash += (int) ordersAccountid;
        hash += (int) videoInfoid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasePK)) {
            return false;
        }
        PurchasePK other = (PurchasePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.ordersid != other.ordersid) {
            return false;
        }
        if (this.ordersAccountid != other.ordersAccountid) {
            return false;
        }
        if (this.videoInfoid != other.videoInfoid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.PurchasePK[ id=" + id + ", ordersid=" + ordersid + ", ordersAccountid=" + ordersAccountid + ", videoInfoid=" + videoInfoid + " ]";
    }
    
}
