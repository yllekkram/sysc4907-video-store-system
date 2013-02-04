/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author LaFamiglia
 */
@Embeddable
public class Order1PK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Column(name = "pendingCharge")
    private int pendingCharge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Account_id")
    private int accountid;

    public Order1PK() {
    }

    public Order1PK(int id, int accountid) {
        this.id = id;
        this.accountid = accountid;
        this.pendingCharge = 0;
    }

    public int getPendingCharge() {
        return pendingCharge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public void setPendingCharge(int pendingCharge) {
        this.pendingCharge = pendingCharge;
    }

    /*
     * Increases the charge when an order is added
     */
    public void increaseCharge(int newCharge) {
        this.pendingCharge += newCharge;
    }
    /*
     * Decreases the charge when an order is removed
     */

    public void decreaseCharge(int oldCharge) {
        if (pendingCharge - oldCharge >= 0) {
            this.pendingCharge -= oldCharge;
        }

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) accountid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1PK)) {
            return false;
        }
        Order1PK other = (Order1PK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.accountid != other.accountid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.Order1PK[ id=" + id + ", accountid=" + accountid + " ]";
    }
}
