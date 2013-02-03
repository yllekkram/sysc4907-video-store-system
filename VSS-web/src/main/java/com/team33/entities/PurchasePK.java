/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samual
 */
@Embeddable
public class PurchasePK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Order_Account_id")
    private int accountId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Order_id")
    private int orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Video_Info_id")
    private int videoInfoId;

    public PurchasePK() {
    }

    public PurchasePK(int accountId, int orderId, int videoInfoId) {
        this.accountId = accountId;
        this.orderId = orderId;
        this.videoInfoId = videoInfoId;
    }

    public PurchasePK(int id, int accountId, int orderId, int videoInfoId) {
        this.id = id;
        this.accountId = accountId;
        this.orderId = orderId;
        this.videoInfoId = videoInfoId;
    }

    public int getId() {
        return this.id;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public int getVideoInfoId() {
        return this.videoInfoId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountId(int id) {
        this.accountId = id;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    public void setVideoInfoId(int id) {
        this.videoInfoId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) accountId;
        hash += (int) orderId;
        hash += (int) videoInfoId;
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
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.videoInfoId != other.videoInfoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.PurchasePK[ id=" + id + " ]";
    }
}
