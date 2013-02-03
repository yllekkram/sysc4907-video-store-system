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
public class InvoicePK implements Serializable {

    @Basic(optional = false)
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
    @Column(name = "orderCharge")
    private int orderCharge;

    public InvoicePK() {
    }

    public InvoicePK(int id, int accountId, int orderId, int orderCharge) {
        this.id = id;
        this.accountId = accountId;
        this.orderId = orderId;
        this.orderCharge = orderCharge;
    }

    public InvoicePK(int accountId, int orderId, int orderCharge) {
        this.accountId = accountId;
        this.orderId = orderId;
        this.orderCharge = orderCharge;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getId() {
        return id;
    }

    public int getOrderCharge() {
        return orderCharge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderCharge(int orderCharge) {
        this.orderCharge = orderCharge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) orderId;
        hash += (int) accountId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoicePK)) {
            return false;
        }
        InvoicePK other = (InvoicePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.accountId != other.accountId) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.team33.entities.InvoicePK[ id=" + id + ", accountid=" + accountId + "orderId" + orderId + "]";
    }
}
